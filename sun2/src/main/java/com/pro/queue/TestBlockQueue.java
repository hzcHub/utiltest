package com.pro.queue;

import java.util.Iterator;
import java.util.concurrent.*;

public class TestBlockQueue {


    public static void main(String[] args) {

        System.out.println("currentThread = "+Thread.currentThread().getName());
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                bq();
            }
        });
        executorService.shutdown();

    }


    public static void bq(){
        BlockingQueue bq = new ArrayBlockingQueue(100);
        bq.add("asda1");
        bq.add("asda2");
        bq.add("asda3");
        bq.add("asda4");
        bq.add("asda5");
        for (int i = 0; i < 80; i++) {
            bq.add(i+"www");
        }
        bq.forEach(System.out::print);

        boolean as = bq.contains("asda");
        System.out.println("as = " + as);

        Object element = bq.element();//firstEle
        System.out.println("element = " + element);
        Iterator iterator = bq.iterator();//顺序取出
        while (iterator.hasNext()){
            System.out.print("iterator = " + iterator.next());
        }
        System.out.println("======");
        System.out.println("currentThread = "+Thread.currentThread().getName());
    }
}
