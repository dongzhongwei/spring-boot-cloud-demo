package com.ddw.demo.pattern.factory.factory_method;

public class ConsoleLoger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("控制台记录日志");
    }
}
