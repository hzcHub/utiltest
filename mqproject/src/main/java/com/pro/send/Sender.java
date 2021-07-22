package com.pro.send;

import com.pro.bean.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public  void  send(){
        amqpTemplate.convertAndSend("queue","简单队列消息");
    }

    public  void  send2(){
        User user = new User();
        user.setAge(10);
        user.setName("士大夫");
        user.setSex("gads");
        amqpTemplate.convertAndSend("queue2",user);
    }
    public  void  send3(){
        amqpTemplate.convertAndSend("exchange.topic","topic.message","send3");
    }
    public  void  send4(){
        amqpTemplate.convertAndSend("exchange.topic","topic.messages","send4");
    }

    public  void  send5(){
        User user = new User();
        user.setAge(10);
        user.setName("士大夫");
        user.setSex("gads");
        amqpTemplate.convertAndSend("exchange.fanout","","asda");
    /*    for (int i =0;i<10;i++){
            amqpTemplate.convertAndSend("exchange.fanout","","");
        }
*/
    }
}
