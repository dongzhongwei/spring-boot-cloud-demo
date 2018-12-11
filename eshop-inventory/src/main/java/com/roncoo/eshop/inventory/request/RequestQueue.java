package com.roncoo.eshop.inventory.request;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class RequestQueue {

    //内存队列
    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    private Map<Integer, Boolean> flagMap =  new ConcurrentHashMap<>();

    private static class Singleton {

        private static RequestQueue instance;

        static {
            instance = new RequestQueue();
        }


        public static RequestQueue getInstance() {
            return instance;
        }
    }

    /**
     * jvm的机制去保证多线程并发安全
     * @return
     */
    public static RequestQueue getInstance(){
        return RequestQueue.Singleton.getInstance();
    }

    public void addQueue(ArrayBlockingQueue<Request> queue){
        queues.add(queue);
    }

    public int queueSize(){
        return queues.size();
    }

    public ArrayBlockingQueue<Request> getQueue(int index){
        return queues.get(index);
    }

    public Map<Integer, Boolean> getFlagMap(){
        return flagMap;
    }

}
