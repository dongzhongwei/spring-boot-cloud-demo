package com.ddw.demo.rpc.server.rmi;

import java.rmi.RemoteException;

public class RMIDemoImpl implements RMIDemo {

    @Override
    public String doCommunicate(String name) throws RemoteException {
        return "\nServer says: Hi "+ name +"\n";
    }
}
