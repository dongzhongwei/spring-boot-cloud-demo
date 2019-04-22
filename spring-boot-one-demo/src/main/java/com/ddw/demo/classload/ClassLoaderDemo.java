package com.ddw.demo.classload;

import org.springframework.util.ClassUtils;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-03-26 14:15
 * @Description
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws Exception {

//        Class.forName("com.ddw.demo.classload.MyObj");

//        System.out.println("--------------------------------");


        Class<?> myObjClass = ClassUtils.forName("com.ddw.demo.classload.MyObj", ClassLoaderDemo.class.getClassLoader());

        myObjClass.newInstance();

//        ClassUtils.getDefaultClassLoader().loadClass("com.ddw.demo.classload.MyObj");
//
//        System.out.println("--------------------------------");
//
//        ClassLoader.getSystemClassLoader().loadClass("com.ddw.demo.classload.MyObj");
//
//        System.out.println("--------------------------------");
//
//        Thread.currentThread().getContextClassLoader().loadClass("com.ddw.demo.classload.MyObj");
    }
}
