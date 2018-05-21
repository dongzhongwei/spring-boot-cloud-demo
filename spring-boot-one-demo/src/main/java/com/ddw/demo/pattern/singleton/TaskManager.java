package com.ddw.demo.pattern.singleton;

public class TaskManager {

    private static TaskManager tm = null;

    private TaskManager(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static TaskManager getInstance(){
        if (tm == null){
            tm = new TaskManager();
        }
        return tm;
    }
}
