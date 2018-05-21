package com.ddw.demo.pattern.factory.factory_method;

public class Client {
    public static void main(String[] args) {
        LoggerFactory loggerFactory = new FileLoggerFactory();
        final Logger logger = loggerFactory.createLogger();
        logger.writeLog();

    }
}
