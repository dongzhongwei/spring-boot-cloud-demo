package com.ddw.demo.io.bio;

import com.ddw.demo.io.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BIOServerHandler implements Runnable {
    private Socket socket;

    public BIOServerHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression;
            String result;
            while (true){
                //通过BufferedReader读取一行
                //如果已经读到输入流尾部，返回null，退出循环
                if ((expression = in.readLine()) == null){
                    break;
                }
                System.out.println("\n服务器收到消息：" + expression);
                result = Calculator.cal(expression).toString();
                out.println(result);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    in = null;
                }
            }

            if (out != null){
                out.close();
                out = null;
            }

            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    socket = null;
                }
            }
        }

    }
}
