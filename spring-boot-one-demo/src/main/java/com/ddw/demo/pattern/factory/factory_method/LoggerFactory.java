package com.ddw.demo.pattern.factory.factory_method;

public interface LoggerFactory {

    Logger createLogger();

    Logger createLogger(String args);

    Logger createLogger(Object obj);
}
