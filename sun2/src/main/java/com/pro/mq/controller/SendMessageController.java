package com.pro.mq.controller;


import com.pro.mq.confg.BackExchange;
import com.pro.mq.confg.DelayedQueueConfg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @RequestMapping("/sendMes/{mes}")
    public void sendMessage(@PathVariable("mes")String mes){

        log.info("发送消息当前时间为{}",new Date().toString());
       //convertAndSend(String exchange, String routingKey, Message message)
        rabbitTemplate.convertAndSend("EA","XA","十秒消息延迟"+mes);
        rabbitTemplate.convertAndSend("EA","XB","4十秒消息延迟"+mes);

    }

/*    @RequestMapping("/sendMes/{mes}/{time}")
    public void sendMessage(@PathVariable("mes")String mes,@PathVariable("time")String time){

        log.info("发送消息当前时间为{}",new Date().toString());
        //convertAndSend(String exchange, String routingKey, Message message)
        rabbitTemplate.convertAndSend("EA","XC",mes, message ->
        {
            message.getMessageProperties().setExpiration(time);
            return message;
        });


    }*/

    @RequestMapping("/sendMes/{mes}/{time}")
    public void sendMessage(@PathVariable("mes")String mes,@PathVariable("time")Integer time){

        log.info("发送消息当前时间为{}",new Date().toString());
        //convertAndSend(String exchange, String routingKey, Message message)
        rabbitTemplate.convertAndSend(DelayedQueueConfg.DELAYED_EXCHANGE,DelayedQueueConfg.DELAYED_ROUTINGKEY,mes, message ->
        {
            message.getMessageProperties().setDelay(time);
            return message;
        });


    }


    @RequestMapping("/sendMes2/{mes}")
    public void sendMessage2(@PathVariable("mes")String mes){


        //测试当前发送失败 exchange写错 交换机确认机制回调 触发失败提示
        // MycallBack ==》 confirm(CorrelationData correlationData, boolean b, java.lang.String s)
        //rabbitTemplate.convertAndSend("错误交换机名称","XA","十秒消息延迟"+mes, correlationData);

        //测试routingkey写错导致找不到队列发送失败进行回退信息
        //MycallBack ==》 returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey)
        //  rabbitTemplate.convertAndSend("EA","错误路由key","十秒消息延迟"+mes, correlationData);

        CorrelationData correlationData = new CorrelationData("1");
        log.info("发送消息当前时间为{}",new Date().toString());
        //convertAndSend(String exchange, String routingKey, Message message)
        rabbitTemplate.convertAndSend("EA1","XA","交换机错误信息"+mes, correlationData);
        rabbitTemplate.convertAndSend("EA","XA1","路由key错误信息"+mes, correlationData);
        rabbitTemplate.convertAndSend("EA","XA","十秒延迟信息"+mes, correlationData);

        rabbitTemplate.convertAndSend(BackExchange.EXCHANGE_CONFIRM,BackExchange.CONFIRM_ROUTING_KEY,"可路由信息"+mes, correlationData);
        rabbitTemplate.convertAndSend(BackExchange.EXCHANGE_CONFIRM,"aa","不可路由信息"+mes, correlationData);

    }

}
