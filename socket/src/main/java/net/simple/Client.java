package net.simple;

import java.io.*;
import java.net.*;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-04-04 22:59
 * @Description
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
        System.out.println("已发起服务器连接");
        System.out.println("客户端：" + socket.getLocalAddress() + " P: " + socket.getLocalPort());
        System.out.println("服务端：" + socket.getInetAddress() + " P: " + socket.getPort());
        try {
            todo(socket);
        } catch (Exception e) {
            System.out.println("异常关闭");
        }
        socket.close();
        System.out.println("客户端已经推出");
    }
    private static void todo(Socket client) throws IOException {
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        //得到socket输出流, 并转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);
        //得到socket输入流
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        boolean flag = true;
        while (flag) {
            String str = input.readLine();
            socketPrintStream.println(str);
            String echo = socketBufferedReader.readLine();
            if ("bye".equals(echo)) {
                flag = false;
            } else {
                System.out.println(echo);
            }
        }
        socketPrintStream.close();
    }
}
