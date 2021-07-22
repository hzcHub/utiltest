package com.pro.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UseMinio {

    public static void main(String[] args)  {

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        UploadUtil uploadUtil = new UploadUtil();

        executorService.submit(uploadUtil);
        executorService.submit(uploadUtil);
        executorService.submit(uploadUtil);
        executorService.submit(new Thread(()->{
            System.out.println("lamb");
        }));

      //  executorService.submit(new BucketListenter());

        //executorService.shutdown();

  /*      Thread thread = new Thread(new MinioUtil());
        thread.start();*/



    }




}
