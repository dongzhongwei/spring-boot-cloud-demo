package com.ddw.demo.pattern.factory.simple_factory;

public class ChartFactoryTest {
    public static void main(String[] args) {
        //通过静态工厂发发创建产品
        //type: histogram、pie、line
        Chart chart = ChartFactory.getChart("histogram");
        chart.display();
    }
}
