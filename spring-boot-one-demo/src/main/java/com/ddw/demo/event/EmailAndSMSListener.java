package com.ddw.demo.event;

/**
 * 同时处理发送邮件事件和短信事件
 */
public class EmailAndSMSListener implements TrainTicketListener {

    @Override
    public void handEvent(TrainTicketEvent tte) {
        if (tte instanceof SendEmailEvent){
            final SendEmailEvent sendEmailEvent = SendEmailEvent.class.cast(tte);
            System.out.println(sendEmailEvent.getEmailData());
        } else if(tte instanceof SendSMSEvent){
            final SendSMSEvent sendSMSEvent = SendSMSEvent.class.cast(tte);
            System.out.println(sendSMSEvent.getSMSData());
        } else {
            System.out.println("发送未知事件，无法处理");
        }
    }
}
