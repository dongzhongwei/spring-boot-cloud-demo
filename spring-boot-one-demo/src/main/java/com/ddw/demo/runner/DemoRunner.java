package com.ddw.demo.runner;

import com.ddw.demo.config.DataSourceConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
public class DemoRunner implements CommandLineRunner {

    @Autowired
    private DataSourceConfigBean dataSourceConfigBean;

//    @Autowired
//    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
//        dataSourceConfigBean.getDatasource();
//        dataSource.getConnection();
//        dataSourceConfigBean.getDatasource();

    }
}
