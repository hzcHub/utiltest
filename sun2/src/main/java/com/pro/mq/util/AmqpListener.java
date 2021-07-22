package com.pro.mq.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AmqpListener {


    @Autowired
    private RabbitMQConfiguration rabbitMQConfiguration;

    @Bean("messageListener")
    public MessageListener exampleListener() {
        return new MessageListener() {
            public void onMessage(Message message) {
                //amqpReceiver.onMessage(message);
                System.out.print("接收消息：" + new String(message.getBody()));
            }
        };
    }
    @Bean("connectionFactory")
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMQConfiguration.getHost());
        connectionFactory.setUsername(rabbitMQConfiguration.getUserName());
        connectionFactory.setPassword(rabbitMQConfiguration.getPassword());
        connectionFactory.setPort(rabbitMQConfiguration.getPort());
        return connectionFactory;
    }
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(@Qualifier("messageListener")MessageListener messageListener
                ,@Qualifier("connectionFactory")ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //设置监听的队列名，数组[]"abc","test4"
        String[] types = {rabbitMQConfiguration.getReceiverQueueName()};
        //container.setQueueNames(types);
        container.addQueueNames(types);
        container.setMessageListener(messageListener);

        return container;
    }



}
