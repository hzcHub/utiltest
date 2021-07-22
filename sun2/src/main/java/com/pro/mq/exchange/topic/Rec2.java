package com.pro.mq.exchange.topic;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Rec2 {


    public static final String EXCHANGE_NAME ="exchange.topic";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();
        String queueName = "Q2";
        channel.queueDeclare(queueName,false,false,false,null);

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //queueBind(String queue, String exchange, String routingKey)
        channel.queueBind(queueName,EXCHANGE_NAME,"*.*.topic");
        System.out.println("2连接成功等待。。。。。。。。。。。");
        DeliverCallback deliverCallback = (var1, var2)->{
            System.out.println("var1 = " + var1);
            System.out.println("var2 = " + new String(var2.getBody(),"utf-8"));
        };
        CancelCallback cancelCallback = (var1)->{

        };
        //basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback)
        channel.basicConsume(queueName,true,deliverCallback,cancelCallback);

    }
}
