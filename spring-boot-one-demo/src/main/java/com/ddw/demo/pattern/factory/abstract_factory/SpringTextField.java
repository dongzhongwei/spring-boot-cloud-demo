package com.ddw.demo.pattern.factory.abstract_factory;

public class SpringTextField implements TextField {
    @Override
    public void display() {
        System.out.println("显示绿色边框文本框。");
    }
}
