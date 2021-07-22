package com.pro.mq.confg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@Slf4j
public class BackExListener {

    @RabbitListener(queues = BackExchange.QUEUED_BACK_WARING)
    public void backQueueListener(Message message){
        String msg ="";
        try {
            msg = new String(message.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("BackExListener backQueueListener 接收到确认交换机发送失败转发至备份交换机的信息，不可路由消息的内容为：{}",msg);
    }

    @RabbitListener(queues = BackExchange.QUEUED_CONFIRM)
    public void confirmQueueListener(Message message){
        String msg ="";
        try {
            msg = new String(message.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("BackExListener confirmQueueListener 接收到确认交换机发送成功，！！！未转发至备份交换机的信息，可路由消息的内容为：{}",msg);
    }


}
