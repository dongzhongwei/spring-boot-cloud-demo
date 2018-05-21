package com.ddw.demo.event;

import java.util.EventListener;

public interface TrainTicketListener extends EventListener {

    void handEvent(TrainTicketEvent tte);
}
