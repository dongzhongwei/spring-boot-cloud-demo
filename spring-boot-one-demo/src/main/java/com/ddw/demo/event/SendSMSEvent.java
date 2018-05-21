package com.ddw.demo.event;

/**
 * 发送短信
 */
public class SendSMSEvent extends TrainTicketEvent {

    private TrainTicket trainTicket;


    public SendSMSEvent(TrainTicket trainTicket) {
        super(trainTicket);
        this.trainTicket = trainTicket;
    }

    public TrainTicket getTrainTicket() {
        return trainTicket;
    }

    public String getSMSData(){
        if (trainTicket != null){
            return "发送短信："+ trainTicket.getUserName()+"您好，已经成功购买火车票"+trainTicket.getTicketName();
        }
        return null;
    }
}
