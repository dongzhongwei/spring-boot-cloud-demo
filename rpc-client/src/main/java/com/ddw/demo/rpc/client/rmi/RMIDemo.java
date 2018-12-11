package com.ddw.demo.rpc.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIDemo extends Remote {

    String doCommunicate(String name) throws RemoteException;

}
