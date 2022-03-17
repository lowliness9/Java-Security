package RMIServerTest;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMITestInterface extends Remote {
    public String test() throws RemoteException;
}
