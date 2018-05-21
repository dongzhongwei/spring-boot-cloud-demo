package com.ddw.demo.jvm;

public class JavaVMStackOOM {
    private void doneStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            new Thread(()->{
                doneStop();
            }).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
