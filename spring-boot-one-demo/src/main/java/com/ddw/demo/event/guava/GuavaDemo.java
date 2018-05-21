package com.ddw.demo.event.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.Executors;

public class GuavaDemo {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new Object() {
            @Subscribe
            public void lister(Integer integer) {
                System.out.printf("%s from int\n", integer);
            }
        });
        eventBus.post(1);
        System.out.println(Runtime.getRuntime().availableProcessors());


        final AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors()));

        asyncEventBus.register(new Object() {
            @Subscribe
            public void lister(Long num) {
                System.out.printf("%s from long\n", num);
            }
        });

        asyncEventBus.post(2L);
    }
}
