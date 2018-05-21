package com.ddw.demo.event;

/**
 * 发送邮件
 */
public class SendEmailEvent extends TrainTicketEvent {

    private TrainTicket trainTicket;


    public SendEmailEvent(TrainTicket trainTicket) {
        super(trainTicket);
        this.trainTicket = trainTicket;
    }

    public TrainTicket getTrainTicket() {
        return trainTicket;
    }

    public String getEmailData(){
        if (trainTicket != null){
            return "发送邮件："+ trainTicket.getUserName()+"您好，已经成功购买火车票"+trainTicket.getTicketName();
        }
        return null;
    }
}
