package com.pro.mq.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class RabbitMQConfiguration {

    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.userName}")
    private String userName;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("${rabbitmq.port}")
    private Integer port;
    @Value("${rabbitmq.sendQueueName}")
    private String sendQueueName;
    @Value("${rabbitmq.receiverQueueName}")
    private String receiverQueueName;


    


}
