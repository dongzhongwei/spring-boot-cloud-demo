package com.ddw.demo.rpc.server.rmi;

import java.rmi.Naming;

public class RMIDemoServer {
    public static void main(String[] args) throws Exception {
        RMIDemo rMIDemo = new RMIDemoImpl();
        Naming.bind("RMIDemo", rMIDemo);
        System.out.println("");
    }
}
