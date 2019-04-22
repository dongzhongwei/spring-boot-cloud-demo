package net.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-04-04 23:36
 * @Description
 */
public class ServerHandler extends Thread {
    private Socket socket;
    private boolean flag = true;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("客户端：" + socket.getInetAddress() + " P: " + socket.getPort());
        try (PrintStream socketOutput = new PrintStream(socket.getOutputStream());
             BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (flag) {
                String str = socketInput.readLine();
                if ("bye".equals(str)) {
                    flag = false;
                    socketOutput.println("bye");
                } else {
                    System.out.println(str);
                    socketOutput.println("回送：" + str.length());
                }
            }
        } catch (Exception e) {
            System.out.println("连接异常断开");
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("客户端已关闭：" + socket.getInetAddress() + " P:" + socket.getPort());
        }
    }
}
