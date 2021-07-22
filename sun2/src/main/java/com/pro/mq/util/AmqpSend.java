package com.pro.mq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;


/**
 * 发送消息mq队列
 */
public class AmqpSend {

    private static Logger log = LoggerFactory.getLogger(AmqpSend.class);

    private AmqpTemplate rabbitTemplate;
    private RabbitMQConfiguration rabbitMQConfiguration;
    private String context;

    public AmqpSend(String context, RabbitMQConfiguration rabbitMQConfiguration, AmqpTemplate rabbitTemplate) {
        this.context = context;
        this.rabbitMQConfiguration = rabbitMQConfiguration;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        try {
            this.rabbitTemplate.convertAndSend(rabbitMQConfiguration.getSendQueueName(), context);
            log.info("消息发送成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("消息发送失败");
        }

    }
}