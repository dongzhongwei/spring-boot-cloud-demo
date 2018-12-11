package com.ddw.demo.thread.guava;

import com.google.common.base.Function;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ListenableFutureDemo {


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
            int num =i;
        long startTime = System.currentTimeMillis();

        ListenableFuture<Integer> future1 = service.submit(() -> {
            Thread.sleep(2000);
            System.out.println(System.currentTimeMillis() + "  call future 1");
            return 1;
        });


        ListenableFuture<Integer> future2 = service.submit(()->{
            Thread.sleep(9000);
            System.out.println(System.currentTimeMillis() + "  call future 2");
            return 2;
        });

        ListenableFuture allFutures = Futures.allAsList(future1, future2);

        final ListenableFuture transform = Futures.transform(allFutures, new Function<List<Integer>, List<String>>() {
            @Override
            public List<String> apply(List<Integer> input) {
                return input.stream().map(i -> "str"+i).collect(Collectors.toList());
            }
        },service);


        //
        Futures.addCallback(transform, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                System.out.println(result.getClass());
                System.out.printf("success with: %s%n", result);
                service.shutdown();
                System.out.println(num+"----"+startTime+":"+ (System.currentTimeMillis()-startTime));
            }
            @Override
            public void onFailure(Throwable t) {
                System.out.printf("onFailure%s%n", t.getMessage());
            }
        },service);

        }
    }
}
