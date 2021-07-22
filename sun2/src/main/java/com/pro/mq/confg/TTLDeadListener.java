package com.pro.mq.confg;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
@Component
public class TTLDeadListener {

    @RabbitListener(queues = "QD")
    public void recvQueueD(Message message, Channel channel) throws UnsupportedEncodingException {
        String msg = new String(message.getBody(),"utf-8");
        System.out.println("接收到死信息 = " + msg);

        log.info("当前时间{},接收到信息{},"+new Date().toString(),msg);
    }

   /* @RabbitListener(queues =DelayedQueueConfg.DELAYED_QUEUE )
    public void recvDelayedQueue(Message message, Channel channel) throws UnsupportedEncodingException {
        String msg = new String(message.getBody(),"utf-8");
        System.out.println("接收到延迟信息 = " + msg);

        log.info("当前时间{},接收到延迟信息{},"+new Date().toString(),msg);
    }*/
}
