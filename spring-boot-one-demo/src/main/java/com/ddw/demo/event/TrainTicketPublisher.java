package com.ddw.demo.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 事件派发器
 *
 * @author wiliam
 */
public class TrainTicketPublisher {

    private List<TrainTicketListener> ttlList = new ArrayList<TrainTicketListener>();
    private static TrainTicketPublisher ttp;

    private TrainTicketPublisher() {
        Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("event-config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("初始化监听器失败" + e.getMessage());
        }
        String listenerStr = props.getProperty("listener");
        String[] listenerClassNames = listenerStr.split(",");
        if (listenerClassNames != null && listenerClassNames.length > 0) {
            for (String listenerClassName : listenerClassNames) {
                try {
                    Class listenerClass = Class.forName(listenerClassName);
                    TrainTicketListener ttl = TrainTicketListener.class.cast(listenerClass.newInstance());
                    ttlList.add(ttl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static TrainTicketPublisher instance() {
        if (ttp == null) {
            ttp = new TrainTicketPublisher();
        }
        return ttp;
    }

    public void publishEvent(TrainTicketEvent tte) {
        for (TrainTicketListener ttl : ttlList) {
            ttl.handEvent(tte);
        }
    }
}