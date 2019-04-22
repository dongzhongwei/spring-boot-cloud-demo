package com.mmall.concurrency.example.threadLocal;

/**
 *
 * @author ddw
 * @version 1.0
 * @date 2019-03-20 15:35
 * @Description
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId(){
        return  requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
