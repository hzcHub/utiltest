package com.pro;

import com.pro.send.Sender;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMq {

    @Autowired
    Sender sender;

    @Test
    public void testSend(){
        sender.send();
    }
    @Test
    public void testSend2(){
        sender.send2();
    }
    @Test
    public void testSend3(){
        sender.send3();
    }
    @Test
    public void testSend4(){
        sender.send4();
    }
    @Test
    public void testSend5() throws UnsupportedEncodingException {
        sender.send5();
        List a = new ArrayList<>();
        System.out.println(a.isEmpty());

    }


}
