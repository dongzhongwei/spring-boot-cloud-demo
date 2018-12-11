package com.ddw.demo.io.aio;

import com.ddw.demo.io.aio.client.AIOClient;

import java.util.Scanner;

public class AIODemo {
    public static void main(String[] args) throws Exception {
        //运行服务器
        AIOServer.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        AIOClient.start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while (AIOClient.sendMsg(scanner.nextLine())) ;
    }
}
