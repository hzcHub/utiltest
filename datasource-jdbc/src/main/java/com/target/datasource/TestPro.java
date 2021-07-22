package com.target.datasource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("TestPro.properties")
//@Component
public class TestPro {

    @Value("${testPro.driver}")
    private String driver;
    @Value("${testPro.url}")
    private String url;
    @Value("${testPro.username}")
    private String username;
    @Value("${testPro.password}")
    private String password;

    public void init() {
        System.out.println("TestPropertise创建后调用初始化方法..........");
    }

    public void destory() {
        System.out.println("TestPropertise销毁后调用销毁方法....通过@Bean的destoryMethod指定销毁方法......");
    }

    public TestPro() {
        System.out.println("TestPro创建完成...通过@Bean的initMethod调用初始化方法............");
    }


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }
}
