package com.pro.mq.exchange.priority;

import com.pro.mq.util.ConnectUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 *
 *
 * 优先级设置  未出现想要的结果=======待测  。。。。。。。。。。。。。 success -->版本太低 不具有优先级功能，亲测3.7之后有此功能
 */
public class PrioritySend {


    public static final String QUEUE_NAME = "pri.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connect = ConnectUtil.getConnect();
        Channel channel = connect.createChannel();
        Map<String,Object> param = new HashMap();
        param.put("x-max-priority",10);
        //ueueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        channel.queueDeclare(QUEUE_NAME,true,false,false,param);

        String msg ="";
        for (int i = 0; i < 100; i++) {
            msg = "priority"+i;
            if (i == 6) {
                AMQP.BasicProperties properties = new AMQP.BasicProperties()
                        .builder().priority(10).build();
                channel.basicPublish("",QUEUE_NAME, properties,msg.getBytes());
            }else {
                channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            }

        }

        channel.close();
        connect.close();

    }
}

