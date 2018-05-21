package com.ddw.demo.event;

public class TestBuyTrainTicketSuccessEvent {
    public static void main(String[] args) {
        TrainTicket trainTicket = new TrainTicket();
        trainTicket.setUserName("小董");
        trainTicket.setTicketName("【北京 -> 安阳】");
        System.out.println("购票成功");
        TrainTicketPublisher.instance().publishEvent(new SendEmailEvent(trainTicket));
        TrainTicketPublisher.instance().publishEvent(new SendSMSEvent(trainTicket));
    }
}
