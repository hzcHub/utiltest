package com.pro.recv;

import com.pro.bean.User;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Recv {

    @RabbitListener(queues = "queue")
    public void process(String s){
        System.out.println("接收的消息1：="+s);
    }
    @RabbitListener(queues = "queue2")
    public void process2(User s){
        System.out.println(s);
        System.out.println("接收实体的消息2：="+s);
    }
    @RabbitListener(queues = "topic.message")
    public void process3(String s){
        System.out.println("接的消息3：="+s);
    }
    @RabbitListener(queues = "topic.messages")
    public void process4(String s){
        System.out.println("接的消息4：="+s);
    }

    @RabbitListener(queues = "fanout.A")
    public void processA(String s){
        System.out.println("接的消息A：="+s);
    }
    @RabbitListener(queues = "fanout.B")
    public void processB(String s){
        System.out.println("接的消息B：="+s);
    }
    @RabbitListener(queues = "fanout.C")
    public void processC(String s){
        System.out.println("接的消息C：="+s);

    }
}
