package com.pro.mq.exchange.fanout;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestRecvExchange2 {

    public static final String EXCHANGE_NAME="exchange.fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connect = ConnectUtil.getConnect();
        // 创建一个通道
        Channel channel = connect.createChannel();

        String queueName = channel.queueDeclare().getQueue();
        //queueBind(String queue, String exchange, String routingKey)
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println("2连接成功等待。。。。。。。。。。。");
        DeliverCallback deliverCallback = ( var1,  var2)->{
            System.out.println("var1 = " + var1);
            System.out.println("var2 = " + new String(var2.getBody(),"utf-8"));
        };
        CancelCallback cancelCallback = (var1)->{

        };
        channel.basicConsume(queueName,true,deliverCallback,cancelCallback);
    }
}
