package com.ddw.demo.pattern.factory.abstract_factory;

public class Client {
    public static void main(String[] args) {
        SkinFactory factory = new SpringSkinFactory();
        final Button button = factory.createButton();
        final TextField textField = factory.createTextField();
        button.display();
        textField.display();


    }
}
