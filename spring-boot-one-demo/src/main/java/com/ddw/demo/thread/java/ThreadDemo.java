package com.ddw.demo.thread.java;

import com.google.common.base.Joiner;

import java.util.*;
import java.util.concurrent.*;

public class ThreadDemo {


    public static void main(String[] args) throws Exception {

        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);
        System.out.println(a != b);
//        System.out.println(a.equals(b));

        //继承Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("继承Thread");
            }
        };
        thread.start();

        //实现Runable接口
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现Runable接口");
            }
        }).start();

        //3.实现callable接口
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable接口";
            }
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
        //不共享数据
        new Thread(() ->{
            int count = 5;
            while (count > 0){
                count --;
                System.out.println("由"+ Thread.currentThread().getName()+"计算，count="+count);
            }
        },"A").start();
        new Thread(() ->{
            int count = 5;
            while (count > 0){
                count --;
                System.out.println("由"+ Thread.currentThread().getName()+"计算，count="+count);
            }
        },"B").start();
        new Thread(() ->{
            int count = 5;
            while (count > 0){
                count --;
                System.out.println("由"+ Thread.currentThread().getName()+"计算，count="+count);
            }
        },"C").start();

        System.out.println(UUID.randomUUID());


//        Thread.sleep(20000);
//        System.out.println("thread start....");

//        Thread t1 = new Thread(() -> System.out.println("t1 start....."),"t1");
//        t1.start();
//
//        Thread.sleep(100);
//
//        new Thread(() -> System.out.println("t2 start....."),"t2").start();
//
//        Thread.sleep(100);
//
//        new Thread(() -> System.out.println("t3 start....."),"t3").start();
//
//        new Thread(() -> System.out.println("t4 start....."),"t4").start();

//        CountDownLatch countDownLatch = new CountDownLatch(100);
//
//        Set<Long> times = new HashSet<>();
//
//        for (int i = 0; i < 100; i++) {
//            Thread thread = new Thread(() -> {
//                System.out.printf("time:%s,%s start...\n", System.currentTimeMillis(),
//                        Thread.currentThread().getName());
//                times.add( System.currentTimeMillis());
//                countDownLatch.countDown();
//            }, "t" + i);
//            System.out.printf("time:%s,threadName:%s,state:%s\n", System.currentTimeMillis(),
//                    thread.getName(), thread.getState());
//            thread.start();
//            System.out.printf("time:%s,threadName:%s,state:%s\n", System.currentTimeMillis(),
//                    thread.getName(), thread.getState());
//        }
//        System.out.println(times.size());
//        countDownLatch.await();
//        t1.join();
//
//        System.out.println("thread end");
    }
}
