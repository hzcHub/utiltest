package com.pro.mq.exchange.dead;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;


public class Rec2 {

	public static final String DEAD_EXCHANGE = "dead.exchange";
	public static final String DEAD_QUEUE = "dead.queue";

	public static void main(String[] args) throws Exception{

		Connection connect = ConnectUtil.getConnect();
		Channel channel = connect.createChannel();
		channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
		channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
		String routingKey ="deadMes";
		channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, routingKey);
		
		System.out.println("wait for message .....");
		DeliverCallback deliverCallback = ( consumerTag,message)->{

			System.out.println("message = "+new String( message.getBody(),"UTF-8"));

		}; 


		channel.basicConsume(DEAD_QUEUE, true, deliverCallback, cancelCallback->{});

		

	}

}
