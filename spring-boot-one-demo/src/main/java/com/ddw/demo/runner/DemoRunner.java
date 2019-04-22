package com.ddw.demo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component
public class DemoRunner implements CommandLineRunner {

//    @Autowired
//    private DataSourceConfigBean dataSourceConfigBean;

//    @Autowired
//    private DataSource dataSource;

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void run(String... args) throws Exception {
//        dataSourceConfigBean.getDatasource();
//        dataSource.getConnection();
//        dataSourceConfigBean.getDatasource();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("system@lanjinghr.com");
        message.setTo("dongzhongwei@126.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        javaMailSender.send(message);

    }
}
