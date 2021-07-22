package com.pro.mq.exchange.fanout;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class TestSendexchange {

    public static final String EXCHANGE_NAME="exchange.fanout";


    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");


       // String message = "交换机分发消息 fanout";

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes(StandardCharsets.UTF_8));
        }

        channel.close();
        connect.close();


    }
}
