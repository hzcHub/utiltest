package com.example.exwar.test;

import java.util.LinkedList;
import java.util.concurrent.*;

public class TestQueue {

    public static void main(String[] args) throws InterruptedException {

        long time1 = System.currentTimeMillis();
          LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>();
         LinkedBlockingDeque<Object> deque = new LinkedBlockingDeque<>();

        LinkedTransferQueue<Object> transferQueue = new LinkedTransferQueue<>();

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        PriorityBlockingQueue<Object> priorityBlockingQueue = new PriorityBlockingQueue<>();


        //LinkedList<Object> objects = new LinkedList<>();

        Runnable r =() -> {
            for (int i = 0; i < 100; i++) {
                //objects.add("a");
               // deque.add("a");
                Thread.currentThread().setName("1");
                //arrayBlockingQueue.add("a");
                priorityBlockingQueue.add("a");
            }

        };

        Runnable r2 =() -> {
            for (int i = 0; i < 100; i++) {
                //objects.add(i);
               // deque.add(i);
                Thread.currentThread().setName("2");
               // arrayBlockingQueue.add("b");
                priorityBlockingQueue.add("b");
            }
        };

        Thread thread1=new Thread(r);
        Thread thread2=new Thread(r2);
       // thread2.isDaemon();
        thread2.setPriority(1);
        thread1.start();

        thread2.start();


       Thread.sleep(1000);
        priorityBlockingQueue.forEach(System.out::println);
        long time2 = System.currentTimeMillis();

        System.out.println(time2-time1);
    }
}
