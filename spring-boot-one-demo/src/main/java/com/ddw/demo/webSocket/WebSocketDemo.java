package com.ddw.demo.webSocket;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.lang.reflect.Type;

public class WebSocketDemo {

    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();


    public static void main(String[] args) throws Exception {
        WebSocketDemo helloClient = new WebSocketDemo();
        String userId = "118";
        ListenableFuture<StompSession> f = helloClient.connect("ws://localhost:8080/socket?userId="+userId);
        StompSession stompSession = f.get();
        System.out.println("Subscribing to greeting topic using session " + stompSession);
        helloClient.subscribeGreetings("/message/"+userId+"/queue/notice", stompSession);
        System.out.println(stompSession);
        Thread.sleep(600000);

//        CountDownLatch latch = new CountDownLatch(1);

        //139.129.203.154
//        for (int i = 0; i < 300; i++) {
//            new Thread(()->{
//                try {
//                    WebSocketDemo helloClient = new WebSocketDemo();
//                    String userId = "118";
//                    ListenableFuture<StompSession> f = helloClient.connect("ws://localhost:8080/socket?userId="+userId);
//                    StompSession stompSession = f.get();
//                    System.out.println("Subscribing to greeting topic using session " + stompSession);
//                    helloClient.subscribeGreetings("/message/"+userId+"/queue/notice", stompSession);
//                    System.out.println(stompSession);
//                    Thread.sleep(600000);
//                } catch (Exception e){
//
//                }
//            }).start();
////            latch.await();
//        }
//        latch.countDown();

//            Thread.sleep(60);
//        Thread.sleep(600000);

    }

    public ListenableFuture<StompSession> connect(String url) {
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);
        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        return stompClient.connect(url, headers, new MyHandler(), "localhost", 8080);
    }

    public void subscribeGreetings(String url, StompSession stompSession) throws ExecutionException, InterruptedException {
        stompSession.subscribe(url, new StompFrameHandler() {
            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                System.out.println("Received greeting " + new String((byte[]) o));
            }
        });
    }

    private class MyHandler extends StompSessionHandlerAdapter {
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            System.out.println("Now connected");
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            exception.printStackTrace();
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            super.handleFrame(headers, payload);
            System.out.println("=========================handleFrame");
        }
    }


}
