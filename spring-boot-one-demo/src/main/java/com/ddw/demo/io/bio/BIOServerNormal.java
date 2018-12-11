package com.ddw.demo.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 服务端源码
 */
public final class BIOServerNormal {

    //默认端口
    private static int DEFAULT_PORT = 12345;

    //单例的ServerSocket
    private static ServerSocket serverSocket;

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
                new Thread(new BIOServerHandler(socket)).start();
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
