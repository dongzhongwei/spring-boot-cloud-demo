package net.simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-04-04 23:20
 * @Description
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(2000);

        System.out.println("服务器准备就绪");
        System.out.println("服务端：" + server.getInetAddress() + " P: " + server.getLocalPort());

        //等待客户端连接
        while (true) {
            Socket socket = server.accept();

            ServerHandler serverHandler = new ServerHandler(socket);

            serverHandler.start();
        }


    }
}
