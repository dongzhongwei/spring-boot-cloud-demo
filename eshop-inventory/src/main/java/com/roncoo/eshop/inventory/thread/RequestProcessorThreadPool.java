package com.roncoo.eshop.inventory.thread;

import com.roncoo.eshop.inventory.request.Request;
import com.roncoo.eshop.inventory.request.RequestQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池 单例
 */
public class RequestProcessorThreadPool {

    //线程池
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public RequestProcessorThreadPool() {

        RequestQueue requestQueue = RequestQueue.getInstance();

        for (int i = 0; i < 10; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }

    }

    private static class Singleton {

        private static RequestProcessorThreadPool instance;

        static {
            instance = new RequestProcessorThreadPool();
        }


        public static RequestProcessorThreadPool getInstance() {
            return instance;
        }
    }

    /**
     * jvm的机制去保证多线程并发安全
     * @return
     */
    public static RequestProcessorThreadPool getInstance(){
        return Singleton.getInstance();
    }

    public static void init(){
        getInstance();
    }
}
