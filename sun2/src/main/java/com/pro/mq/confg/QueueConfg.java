package com.pro.mq.confg;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfg {

    public static final String QUEUEA_NAME="QA";//普通 延迟十秒
    public static final String QUEUEB_NAME="QB";//普通 延迟4十秒
    public static final String QUEUEC_NAME="QC";//普通 延迟自定义
    public static final String QUEUED_NAME="QD";//死信 queue


    public static final String EXCHANGEA_NAME="EA";//普通交换机
    public static final String EXCHANGED_NAME="ED";//死信交换机


    public static final String ROUTINGKEY_XA="XA";//routingKey
    public static final String ROUTINGKEY_XB="XB";//routingKey
    public static final String ROUTINGKEY_XC="XC";//routingKey
    public static final String ROUTINGKEY_XD="XD";//routingKey


    @Bean("exchangea")
    public DirectExchange exchangea(){
        return ExchangeBuilder.directExchange(EXCHANGEA_NAME).build();
    }

    @Bean("exchanged")
    public DirectExchange exchanged(){
        //return ExchangeBuilder.directExchange(EXCHANGED_NAME).build();
        return  new DirectExchange(EXCHANGED_NAME);
    }




    @Bean("queuea")
    public Queue queuea(){

        Map param = new HashMap<>(3);
        param.put("x-dead-letter-exchange",EXCHANGED_NAME);
        param.put("x-dead-letter-routing-key",ROUTINGKEY_XD);
        param.put("x-message-ttl",10000);

        return QueueBuilder.durable(QUEUEA_NAME).withArguments(param).build();
    }
    @Bean("queueb")
    public Queue queueb(){
        Map param = new HashMap<>(3);
        param.put("x-dead-letter-exchange",EXCHANGED_NAME);
        param.put("x-dead-letter-routing-key",ROUTINGKEY_XD);
        param.put("x-message-ttl",40000);
        return QueueBuilder.durable(QUEUEB_NAME).withArguments(param).build();
    }

    @Bean("queuec")
    public Queue queuec(){
        Map param = new HashMap<>(3);
        param.put("x-dead-letter-exchange",EXCHANGED_NAME);
        param.put("x-dead-letter-routing-key",ROUTINGKEY_XD);
        return QueueBuilder.durable(QUEUEC_NAME).withArguments(param).build();
    }

    @Bean("queued")
    public Queue queued(){
        return QueueBuilder.durable(QUEUED_NAME).build();
    }



    @Bean
    public Binding queueaBindExchangea(@Qualifier("queuea")Queue queue,@Qualifier("exchangea")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_XA);
    }

    @Bean
    public Binding queuebBindExchangea(@Qualifier("queueb")Queue queue,@Qualifier("exchangea")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_XB);
    }
    @Bean
    public Binding queuebcindExchangea(@Qualifier("queuec")Queue queue,@Qualifier("exchangea")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_XC);
    }
    @Bean
    public Binding queuedBindExchanged(@Qualifier("queued")Queue queue,@Qualifier("exchanged")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_XD);
    }




}
