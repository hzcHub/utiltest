package com.pro.mq.confg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;


/**
 * 发布确认=============
 *  配置为
 *        publisher-confirm-type: correlated
 *
 *         public static enum ConfirmType {
 *
 *         SIMPLE,  单条确认机制
 *         CORRELATED, 批量确认机制
 *         NONE; 不确认机制  （默认）
 *
 *         private ConfirmType() {
 *         }
 *     }
 */
@Slf4j
@Component
public class MycallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initCallBack(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 交换机 宕机
     * @param correlationData
     * @param b
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, java.lang.String s) {
        //String id = correlationData != null ? correlationData.getId():"";
        Object o = null != correlationData ? correlationData : correlationData.getId();
        if(b){
            log.info("confirm 交换机收到当前消息id为{},发送成功",o);
        }else {
            log.info("confirm 交换机未收到当前消息id为{},发送失败",o);

        }

    }
    /**
     * 队列丢失 交换机将信息返回给发送者
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    //Message message, int replyCode, String replyText, String exchange, String routingKey
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String correlationId = message.getMessageProperties().getCorrelationId();
         String mes = "";
        try {
             mes = new String(message.getBody(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("returnedMessage 交换机：{}未找到接收消息的routingKey为{}，消息id为：{};内容为{}，详细原因为：{}",exchange,routingKey
                ,correlationId,mes,replyText);

    }



}
