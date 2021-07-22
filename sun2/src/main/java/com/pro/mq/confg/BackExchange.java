package com.pro.mq.confg;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/***
 * 备份交换机  使用方法
 *  确认交换机发送失败时  [ 转发至备份交换机 ]，由备份交换机将信息发送到备份队列及提醒队列中
 */
@Configuration
public class BackExchange {

    public static final String QUEUED_CONFIRM="queue.confirm";//确认队列
    public static final String EXCHANGE_CONFIRM="exchange.confirm";//确认交换机
    public static final String CONFIRM_ROUTING_KEY="conf";//确认交换机

    public static final String QUEUED_BACK_INFO="queueInfo";//备份队列
    public static final String QUEUED_BACK_WARING="queueWaring";//备份队列
    public static final String EXCHANGED_BACK="EBACK";//备份交换机



    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange(EXCHANGE_CONFIRM).durable(true).withArgument("alternate-exchange",EXCHANGED_BACK).build();
    }

    @Bean("exchangeback")
    public FanoutExchange exchangeback(){
        return ExchangeBuilder.fanoutExchange(EXCHANGED_BACK).build();
    }

    @Bean("queueInfo")
    public Queue queueInfo(){
        return QueueBuilder.durable(QUEUED_BACK_INFO).build();
    }
    @Bean("queueWaring")
    public Queue queueWaring(){
        return QueueBuilder.durable(QUEUED_BACK_WARING).build();
    }
    @Bean("queueConfirm")
    public Queue queueConfirm(){
        return QueueBuilder.durable(QUEUED_CONFIRM).build();
    }


    @Bean
    public Binding queueConfirmBindConfirmExchange(@Qualifier("queueConfirm")Queue queueConfirm,@Qualifier("confirmExchange")DirectExchange confirmExchange){
        return BindingBuilder.bind(queueConfirm).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }

    @Bean
    public Binding queueInfoBindExchangeback(@Qualifier("queueInfo")Queue queueInfo, @Qualifier("exchangeback")FanoutExchange exchangeback){
        return BindingBuilder.bind(queueInfo).to(exchangeback);
    }

    @Bean
    public Binding queueWaringBindExchangeback(@Qualifier("queueWaring")Queue queueWaring,@Qualifier("exchangeback")FanoutExchange exchangeback){
        return BindingBuilder.bind(queueWaring).to(exchangeback);
    }




}

