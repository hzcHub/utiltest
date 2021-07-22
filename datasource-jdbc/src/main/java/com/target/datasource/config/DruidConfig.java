package com.target.datasource.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.zaxxer.hikari.util.DriverDataSource;
import org.apache.tomcat.websocket.server.WsFilter;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {


    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    @Bean
    public  DataSource druid(){
        DataSource dataSource = new DruidDataSource();
        return dataSource;
    }


    // 后台监控
    @Bean
    public ServletRegistrationBean statViewServlet(){

        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map param = new HashMap<>();
//    public static final String SESSION_USER_KEY = "druid-user";
//    public static final String PARAM_NAME_USERNAME = "loginUsername";
//    public static final String PARAM_NAME_PASSWORD = "loginPassword";
//    public static final String PARAM_NAME_ALLOW = "allow";
//    public static final String PARAM_NAME_DENY = "deny";
//    public static final String PARAM_REMOTE_ADDR = "remoteAddress";

        param.put("loginUsername","admin");
        param.put("loginPassword","123456");
        param.put("allow","");

        bean.setInitParameters(param);
        return bean;
    }


    @Bean
    public FilterRegistrationBean weStatFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        Map param = new HashMap<>();
        param.put("exclusions",".js*.css,/druid/*");

        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.setInitParameters(param);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        return filterRegistrationBean;
    }


    // 后台监控
    /*@Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        HashMap<String, String> initParameters = new HashMap<>();
        // 增加配置项
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");
        initParameters.put("allow","localhost");     //如果为空所有人都可以访问，如果localhost本机可以访问，如果具体的ip值则具体的值
        // 关于其他配置可以在类ResourceServlet下查看

        // 禁止xu访问       initParameters.put("xu","192.168.1.**");

        // 后台需要有人登陆
        bean.setInitParameters(initParameters);
        return bean;
    }*/




}
