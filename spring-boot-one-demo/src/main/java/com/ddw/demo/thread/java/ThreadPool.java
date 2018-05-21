package com.ddw.demo.thread.java;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.StreamUtils;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPool {

//    a. 每次new Thread新建对象性能差。
//    b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
//    c. 缺乏更多功能，如定时执行、定期执行、线程中断。
//
//    相比new Thread，Java提供的四种线程池的好处在于：
//
//    a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
//    b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
//    c. 提供定时执行、定期执行、单线程、并发数控制等功能。

//    newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
//    newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//    newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
//    newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
     */
    public static void cachedThreadPoolTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行" + index);
                }
            });
        }
        //关闭线程池
        cachedThreadPool.shutdown();
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * 因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字
     */
    public static void fixedThreadPoolTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedThreadPool.shutdown();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下
     */
    public static void scheduledThreadPoolTest() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        //延迟3秒
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);

        //延迟1秒后每3秒执行一次
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    public static void singleThreadExecutorTest() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "正在执行" + index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        fixedThreadPoolTest();
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1,
//                new ThreadFactoryBuilder()
//                        .setNameFormat("DiscoveryClient-InstanceInfoReplicator-%d")
//                        .setDaemon(true)
//                        .build());
//        scheduler.shutdown();
//        final Future<?> future = scheduler.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.printf("开始");
//            }
//        });
////        scheduler.shutdown();
//        if (future.isDone()){
//            try {
//                future.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        scheduler.shutdown();
    }
}
