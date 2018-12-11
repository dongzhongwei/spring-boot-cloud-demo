package com.ddw.demo.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO 服务端源码
 */
public final class BIOServerBetter {

    //默认端口
    private static int DEFAULT_PORT = 12345;

    //单例的ServerSocket
    private static ServerSocket serverSocket;

    //线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(60);

    public static void start() throws IOException {
        //使用默认值
        start(DEFAULT_PORT);
    }

    private synchronized static void start(int port) throws IOException {
        if (serverSocket != null){
            return;
        }
        try {

            serverSocket = new ServerSocket(port);

            System.out.println("服务器已启动，端口号："+ port);

            //通过无限玄幻监听客户端连接
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("服务端："+socket);

                executorService.submit(new BIOServerHandler(socket));

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (serverSocket != null){
                System.out.println("服务器已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
