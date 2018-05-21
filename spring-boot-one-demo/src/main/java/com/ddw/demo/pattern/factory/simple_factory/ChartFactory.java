package com.ddw.demo.pattern.factory.simple_factory;

/**
 * 图表工厂类：工厂类
 */
public class ChartFactory {

    //静态工厂方法
    public static Chart getChart(String type){
        Chart chart;
        if ("histogram".equalsIgnoreCase(type)){
            chart = new HistogramChart();
            System.out.println("初始化设置柱状图！");
        } else if ("pie".equalsIgnoreCase(type)){
            chart = new PieChart();
            System.out.println("初始化设置饼状图！");
        } else if ("line".equalsIgnoreCase(type)){
            chart = new LineChart();
            System.out.println("初始化设置折线图！");
        } else {
            chart = null;
            System.out.println("没有对应的图表！");
        }
        return chart;
    }
}
