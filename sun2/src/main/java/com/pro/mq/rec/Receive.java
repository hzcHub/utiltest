package com.pro.mq.rec;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Receive {

    private final static String QUEUE_NAME = "mirror.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connect = ConnectUtil.getConnect();
        // 创建一个通道
        Channel channel = connect.createChannel();
        // 声明队列,由于生产者那边已经声明过，这里可以省略
        /**
         * 1 队列名称
         * 2是否持久化
         * 3是否共享消息，自己消费完其他消费者收不到此条消息
         * 4是否自动删除
         * 5其他参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //channel.basicConsume()

        // 定义一个消费者
        final Map<String, Object> singletonObjects =new HashMap();
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 收到消息会触发这个方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msgString = new String(body, "utf-8");
                System.out.println("[Consumer1] --- receive msg:" + msgString);
                System.out.println("properties = " + properties);
                //channel.basicAck(envelope.getDeliveryTag(),false);//手动签收
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[Consumer1] --- done");
                }
            }
        };
        // 监听
        /**
         * 1队列名称
         * 2是否自动应答 autoACK  --》
         *          fase 时需要
         *          channel.basicAck(envelope.getDeliveryTag(),false);//手动签收 其中false为是否批量应当 最好使用false
         * 3消费回掉callback
         */
        channel.basicConsume(QUEUE_NAME, true, consumer);



    }
}
