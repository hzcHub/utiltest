package com.pro.mq.exchange.dead;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 拒绝消息成为死信
 */
public class RecNew1 {


    public static final String NORMAL_EXCHANGE = "normal.exchange";
    public static final String DEAD_EXCHANGE = "dead.exchange";
    public static final String NORMAL_QUEUE = "normal.queue";
    public static final String DEAD_QUEUE = "dead.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();

        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        Map<String, Object> arguments = new HashMap<>();
       // arguments.put("x-message-ttl",10000);//过期时间 发送方设置
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key","deadMes");
        //设置接受信息数量最大值
        arguments.put("x-max-length",6);
        
        
        
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,arguments);
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);

        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"tiOut");
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"deadMes");

        System.out.println("连接成功等待接受信息。。。。。。。。。");

        DeliverCallback deliverCallback = (var1, var2)->{
            String s = new String(var2.getBody(), "utf-8");
            if(s.equals("message01234")){
                System.out.println("jujuevar2 = " + new String(var2.getBody(),"utf-8"));
                channel.basicReject(var2.getEnvelope().getDeliveryTag(),false);
            }else{
                System.out.println("var2 = " + new String(var2.getBody(),"utf-8"));
                channel.basicAck(var2.getEnvelope().getDeliveryTag(),true);
            }
        };
        CancelCallback cancelCallback =(var1)->{};
        // basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback)
        channel.basicConsume(NORMAL_QUEUE,false,deliverCallback,cancelCallback);







    }
}
