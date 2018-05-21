package com.ddw.demo.pattern.singleton;

import java.util.concurrent.CountDownLatch;

public class TaskManagerTest {
    public static void main(String[] args) {
        //单线程
//        for (int i = 0; i < 10; i++) {
//            System.out.println(TaskManager.getInstance());
//        }

        //多线程
        CountDownLatch cdl = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Thread:%s,TaskManager:%s\n",Thread.currentThread().getName(), TaskManager.getInstance());
            }).start();
        }
        cdl.countDown();

    }
}
