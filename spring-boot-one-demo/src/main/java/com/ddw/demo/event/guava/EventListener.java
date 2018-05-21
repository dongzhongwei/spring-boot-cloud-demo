package com.ddw.demo.event.guava;

import com.google.common.eventbus.Subscribe;

public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("ThreadName:"+Thread.currentThread().getName()+",Message:" + lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
