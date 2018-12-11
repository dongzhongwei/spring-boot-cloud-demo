package com.ddw.demo.io.nio;

import java.util.Scanner;

public class NIODemo {
    public static void main(String[] args) throws Exception {
        NIOServer.start();

        Thread.sleep(1000);

        NIOClient.start();

        while (NIOClient.sendMsg(new Scanner(System.in).nextLine()));
    }
}
