package com.ddw.demo.event.guava;

public class TestEvent {
    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("ThreadName:"+Thread.currentThread().getName()+",event message:"+message);
    }

    public int getMessage(){
        return message;
    }
}
