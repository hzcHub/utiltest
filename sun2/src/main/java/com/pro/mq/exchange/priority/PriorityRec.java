package com.pro.mq.exchange.priority;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PriorityRec {

    public static final String QUEUE_NAME = "pri.queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();

        DeliverCallback deliverCallback = (a,b)->{
            System.out.println("接收到优先级排序信息"+new String(b.getBody(),"utf-8"));
        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,s->{});


    }


}
