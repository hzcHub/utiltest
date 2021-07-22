package com.pro.mq.confg;


import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * 延迟队列设置  通过插件实现
 */
//@Configuration
public class DelayedQueueConfg {

    public static final String DELAYED_EXCHANGE="delayed.exchange";
    public static final String DELAYED_QUEUE="delayed.queue";
    public static final String DELAYED_ROUTINGKEY="delayed.routingkey";


    @Bean("delayQueue")
    public Queue delayQueue(){
        return QueueBuilder.durable(DELAYED_QUEUE).build();
    }

    @Bean("delayedExchange")
    public CustomExchange delayedExchange(){

        Map param = new HashMap<>();
        param.put("x-delayed-type",BuiltinExchangeType.DIRECT);

        return new CustomExchange(DELAYED_EXCHANGE,"x-delayed-message",true
                ,false,param);
    }

    @Bean
    public Binding delayQueueBindDelayedExchange(@Qualifier("delayQueue") Queue delayQueue,
                                                 @Qualifier("delayedExchange") CustomExchange delayedExchange){

        return BindingBuilder.bind(delayQueue).to(delayedExchange)
                .with(DELAYED_ROUTINGKEY).noargs();
    }

}
