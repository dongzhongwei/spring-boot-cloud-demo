package com.ddw.demo.event.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

public class TestEventBus {

    public static void main(String[] args) {
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
//        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("ThreadName:"+Thread.currentThread().getName()+",LastMessage:" + listener.getLastMessage());
    }
}
