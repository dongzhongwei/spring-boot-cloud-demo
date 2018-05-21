package com.ddw.demo.event;

import java.util.EventObject;

/**
 * 购票事件基类
 */
public class TrainTicketEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TrainTicketEvent(Object source) {
        super(source);
    }
}
