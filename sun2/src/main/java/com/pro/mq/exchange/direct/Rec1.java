package com.pro.mq.exchange.direct;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Rec1 {

    public static final String EXCHANGE_NAME = "exchange.direct";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = "listenInfos";

        channel.queueDeclare(queueName,false,false,false,null);
        //queueBind(String queue, String exchange, String routingKey)
        channel.queueBind(queueName,EXCHANGE_NAME,"info");
        channel.queueBind(queueName,EXCHANGE_NAME,"waring");

        //basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback)
        DeliverCallback deliverCallback = ( var1,  var2)->{
            System.out.println("var2 = " + new String(var2.getBody(),"utf-8"));
        };
        CancelCallback cancelCallback =( var1)->{};
        channel.basicConsume(queueName,true,deliverCallback,cancelCallback);

    }
}
