package com.ddw.demo.pattern.factory.factory_method;

public class DatabaseLoggerFactory implements LoggerFactory {



    @Override
    public Logger createLogger() {
        //使用默认方式连接数据库
        Logger logger = new DatabaseLogger();
        //出事数据库日志记录器
        return logger;
    }

    @Override
    public Logger createLogger(String args) {
        //使用参数args作为连接字符串来连接数据库
        Logger logger = new DatabaseLogger();
        //初始化数据库日志记录器，代码省略
        return logger;
    }

    @Override
    public Logger createLogger(Object obj) {
        //使用封装在参数obj中的连接字符串来连接数据库，代码省略
        Logger logger = new DatabaseLogger();
        //使用封装在参数obj中的数据来初始化数据库日志记录器，代码省略
        return logger;
    }
}
