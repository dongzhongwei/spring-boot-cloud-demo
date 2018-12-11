package com.ddw.demo.io.nio;

public class NIOServer {

    private static int DEFAULT_PORT = 12345;

    private static NIOServerHandler serverHandler;

    public static void start() {
        start(DEFAULT_PORT);
    }

    private synchronized static void start(int port) {
        if (serverHandler != null) {
            serverHandler.stop();
        }
        serverHandler = new NIOServerHandler(port);
        new Thread(serverHandler, "NIO-Server").start();
    }

    public static void main(String[] args) {
        start();
    }
}
