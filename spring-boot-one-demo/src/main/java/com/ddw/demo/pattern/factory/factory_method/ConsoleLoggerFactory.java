package com.ddw.demo.pattern.factory.factory_method;

public class ConsoleLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        //创建控制台日志记录器对象
        Logger logger = new ConsoleLoger();
        return logger;
    }

    @Override
    public Logger createLogger(String args) {
        return null;
    }

    @Override
    public Logger createLogger(Object obj) {
        return null;
    }
}
