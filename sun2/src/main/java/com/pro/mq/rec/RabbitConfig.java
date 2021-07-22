package com.pro.mq.rec;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitConfig {

    @Autowired
    CachingConnectionFactory cachingConnectionFactory;



}
