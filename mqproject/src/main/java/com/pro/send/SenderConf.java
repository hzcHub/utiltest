package com.pro.send;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.form.TagWriter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
public class SenderConf {


    @Bean
    public Queue queue() {
        return new Queue("queue");
    }

    @Bean
    public Queue queue2() {
        return new Queue("queue2");
    }

    @Bean(name = "message")
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean(name = "messages")
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }

    @Bean
    public TopicExchange exchange() {
        TopicExchange exchange = new TopicExchange("exchange.topic");//Topic转发模式
        return exchange;
    }

    @Bean
    public Binding bindingExchangeMessage(@Qualifier("message")  Queue queueMessage, TopicExchange topicExchange) {

        return BindingBuilder.bind(queueMessage).to(topicExchange).with("topic.message");
    }

    @Bean
    public Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange topicExchange) {

        return BindingBuilder.bind(queueMessages).to(topicExchange).with("topic.#");
    }


    @Bean(name = "Amessage")
    public Queue Amessage() {
        return new Queue("fanout.A");
    }
    @Bean(name = "Bmessage")
    public Queue Bmessage() {
        return new Queue("fanout.B");
    }
    @Bean(name = "Cmessage")
    public Queue Cmessage() {
        return new Queue("fanout.C");
    }

    @Bean
    public FanoutExchange declarExchange(){
        return new FanoutExchange("exchange.fanout");//配置广播路由器
    }

    @Bean
    public Binding bindingAmessage(@Qualifier("Amessage") Queue queue,FanoutExchange fanoutExchange){

        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    @Bean
    public Binding bindingBmessage(@Qualifier("Bmessage") Queue queue,FanoutExchange fanoutExchange){

        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    @Bean
    public Binding bindingCmessage(@Qualifier("Cmessage") Queue queue,FanoutExchange fanoutExchange){

        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    //private final TagWriter.SafeWriter writer;
    public static void writeAttribute(String attributeName, String attributeValue)  {

        StringBuffer writer  = new StringBuffer();
            writer.append(" ").append(attributeName).append("=\"").append(attributeValue).append("\"");

        System.out.println(writer.toString());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        writeAttribute("name","value");
       HttpServletRequest request = new HttpServletRequest();
        Cookie cookie = WebUtils.getCookie(request, this.getCookieName());
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        /*Set<BeanDefinition> candidates = new LinkedHashSet<>();

                 ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
                 //这里特别注意一下类路径必须这样写
                 //获取指定包下的所有类
                 Resource[] resources = resourcePatternResolver.getResources("classpath*:com/pro//send/*");

                 MetadataReaderFactory metadata=new SimpleMetadataReaderFactory();
                 for(Resource resource:resources) {
                         System.out.println(resource);
                         MetadataReader metadataReader=metadata.getMetadataReader(resource);
                         ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
                         sbd.setResource(resource);
                         sbd.setSource(resource);
                         candidates.add(sbd);
                     }
                 for(BeanDefinition beanDefinition : candidates) {
                         String classname=beanDefinition.getBeanClassName();
                         //扫描controller注解
                     Configuration c=Class.forName(classname).getAnnotation(Configuration.class);
                         //扫描Service注解
                        // Service s=Class.forName(classname).getAnnotation(Service.class);
                         //扫描Component注解
                        // Component component=Class.forName(classname).getAnnotation(Component.class);
                         if(c!=null *//*||s!=null ||component!=null*//*){
                                 System.out.println(classname);
                             }
                     }*/
    }
}
