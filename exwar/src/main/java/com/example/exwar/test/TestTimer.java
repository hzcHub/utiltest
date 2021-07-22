package com.example.exwar.test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class TestTimer {


    private static int nu;

    public static void main(String[] args) throws InterruptedException {


        //Timer1();
        timer2();

        Thread thread = new Thread(new MyThread(),"asdas");
        thread.setName("asdas");
        Thread.State state = thread.getState();
        System.out.println("state = " + state);
        // thread.start();
        Thread.State state2 = thread.getState();
        System.out.println("state2 = " + state2);
        thread.wait(1000);
        Thread.State state3 = thread.getState();
        System.out.println("state3 = " + state3);

        Thread.State state4 = thread.getState();
        System.out.println("state4 = " + state4);
    }


        static class MyThread extends Thread{

            @Override
            public void run() {
//                while (true){
//                    System.out.println("true = " + true);
//                }

                System.out.println("asd");
            }
        }

    static void Timer1() {
        Timer timer = new Timer();
        /*timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("执行方法");

                    }
                },0,5*1000);*/

        timer.schedule(new MyTimerTask(), 0, 1 * 1000);

        // timer.cancel();
    }


    static class MyTimerTask extends TimerTask {

        private int num;

        public MyTimerTask() {
            num += 1;
            System.out.println("MyTimerTask 创建");
            System.out.println("num = " + num);
        }

        @Override
        public void run() {
            Thread.currentThread().setName("定时线程");
            times();


        }


    }


    public static void times() {
        nu += 1;
        System.out.println("times执行方法" + nu + "次" + "时间" + System.currentTimeMillis());


        String name = Thread.currentThread().getName();
        System.out.println("name = " + name);
          /*  System.out.println("name = " + name+" 停止");
            Thread.currentThread().stop();*/

    }


    static void timer2() {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("i = " + i);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(i*2);


//        executor.scheduleAtFixedRate(new Runnable() {
//                                         @Override
//                                         public void run() {
//
//                                         }
//                                     }, 0, 1000, TimeUnit.MILLISECONDS);

        Runnable runnable=()->times();
        Thread.State state = Thread.currentThread().getState();
        System.out.println("state = " + state);

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(runnable
                , 0, 1000, TimeUnit.MILLISECONDS);
        Thread.State state2 = Thread.currentThread().getState();
        System.out.println("state =2 " + state2);



    }


}
