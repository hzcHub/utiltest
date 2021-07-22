package com.pro.mq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;


public class ConnectUtil {


    public static Connection getConnect() throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("192.168.1.200");//keepalived vip ip=192.168.1.200 -->192.168.1.104、192.168.1.105 两个实际地址 通过haproxy代理 三个节点服务
        //factory.setHost("localhost");

        factory.setUsername("admin");
        factory.setPassword("123");
        factory.setPort(5670);
        factory.setVirtualHost("/");
        // 创建一个连接
       return factory.newConnection();
    }


}
