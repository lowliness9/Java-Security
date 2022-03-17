package RMIServerTest;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServerTest {
    public static final String HOST = "0.0.0.0";
    public static final int PORT = 1099;
    public static final String RMIAddress = "rmi://" + HOST + ":" + PORT + "/test";

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

        LocateRegistry.createRegistry(PORT);

        Naming.bind(RMIAddress,new RMITestImpl());

        System.out.println("RMI Server: start  " + RMIAddress);

    }
}
