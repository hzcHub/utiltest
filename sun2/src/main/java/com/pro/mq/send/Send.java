package com.pro.mq.send;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.pro.mq.util.ConnectUtil;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;


//@Scheduled
public class Send {

    private final static String QUEUE_NAME = "mirror.queue";



    public static void main(String[] args) throws IOException, TimeoutException {
        
        Connection connect = ConnectUtil.getConnect();
        // 创建一个通道
        Channel channel = connect.createChannel();
        // 指定一个队列,不存在的话自动创建
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 发送消息
        AMQP.BasicProperties.Builder properties = MessageProperties.PERSISTENT_TEXT_PLAIN.builder();
        properties.messageId("消息ID");
        properties.deliveryMode(2);
        String message = "Hello World!";
        for (int i=0;i<10;i++){
            channel.basicPublish("", QUEUE_NAME, properties.build(), (message+i).getBytes());
            System.out.println(" [x] Sent '" + message +i+ "'");
        }
/**
 * 发布消息
 * 发布到不存在的交换器将导致信道级协议异常，该协议关闭信道，
 * exchange: 要将消息发送到的交换器
 * routingKey: 路由KEY
 * props: 消息的其它属性，如：路由头等
 * body: 消息体
 */
    /*    AMQP.BasicProperties.Builder properties = MessageProperties.PERSISTENT_TEXT_PLAIN.builder();
        properties.messageId("消息ID");
        properties.deliveryMode(2);*/

      /*  String EXCHANGE_NAME  = "";
        String ROUTING_KEY = "";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties.build(), message.getBytes());
*/
        // 关闭频道和连接
        channel.close();
        connect.close();









    }





}
