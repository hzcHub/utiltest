package com.pro.mq.confirm;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

/**
 * 发布确认机制
 * 1 逐条确认
 * 2 批量确认
 * 3 异步批量确认
 *
 *
 */
public class TestConfirm {

    public static final int SENDCOUNT = 1000;

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        //singleConfirm();//用时 = 1397毫秒 1101 1019 929
       // batchConfirm();//用时 = 239毫秒 297
        confirmAsync();//用时 = 90毫秒    基本10ms以内
    }

    public static void singleConfirm() throws IOException, TimeoutException, InterruptedException {
        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,false,false,false,null);
        channel.confirmSelect();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < SENDCOUNT; i++) {
            String message = i+"";
            channel.basicPublish("",queueName,null,message.getBytes(StandardCharsets.UTF_8));
            boolean b = channel.waitForConfirms();
            if(b){
                System.out.println("发送成功");
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("用时 = " + (end - begin) +"毫秒");
        channel.close();
        connect.close();
    }

    public static void batchConfirm() throws IOException, TimeoutException, InterruptedException {
        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,false,false,false,null);
        channel.confirmSelect();

        int confirmNu = 100;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < SENDCOUNT; i++) {
            String message = i+"";
            channel.basicPublish("",queueName,null,message.getBytes(StandardCharsets.UTF_8));
            if((i+1)%confirmNu == 0){
                boolean b = channel.waitForConfirms();
                if(b){
                    System.out.println("发送成功");
                }
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("用时 = " + (end - begin) +"毫秒");
        channel.close();
        connect.close();
    }
    public static void confirmAsync() throws IOException, TimeoutException {

        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,false,false,false,null);

        channel.confirmSelect();
        ConcurrentSkipListMap<Object,String> allConfirmMap = new ConcurrentSkipListMap();

        long begin = System.currentTimeMillis();

        ConfirmCallback confirmCallback = ( var1,  var3) ->{//成功回调

            if(var3){//批量
                ConcurrentNavigableMap<Object, String> headMap = allConfirmMap.headMap(var1);//被确认的消息
                System.out.println("headMap = " + headMap);
                headMap.clear();
            }else {
                allConfirmMap.remove(var1);
            }
            System.out.println("发送成功"+var1);

        } ;
        ConfirmCallback confirmCallback1 =( var1,  var3) ->{//失败回调
            System.out.println("发送失败"+var1);
        } ;
        channel.addConfirmListener(confirmCallback,confirmCallback1);

        for (int i = 0; i < SENDCOUNT; i++) {
            String message = i+"";
            channel.basicPublish("",queueName,null,message.getBytes(StandardCharsets.UTF_8));
            allConfirmMap.put(channel.getNextPublishSeqNo(),message);
        }


        long end = System.currentTimeMillis();

        System.out.println("用时 = " + (end - begin) +"毫秒");


        channel.close();
        connect.close();

    }


}
