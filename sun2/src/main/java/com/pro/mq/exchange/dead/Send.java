package com.pro.mq.exchange.dead;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {


    public static final String NORMAL_EXCHANGE = "normal.exchange";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();

        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        
        BasicProperties basicProperties = new BasicProperties()
        		.builder()
        		.expiration("10000").build();
        
        String message = "message";
        String routingKey = "tiOut";
        
        for (int i = 0; i < 10; i++) {
        	message = message+i;
			channel.basicPublish(NORMAL_EXCHANGE,routingKey, basicProperties,message.getBytes());
		}

        channel.close();
		connect.close();
    }

}
