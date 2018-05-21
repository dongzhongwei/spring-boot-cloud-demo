package com.ddw.demo.beans;

import com.ddw.demo.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringPropertyEditorDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("application-beans.xml");
        context.refresh();

        User user = context.getBean("user",User.class);

        System.out.println(user);

    }
}
