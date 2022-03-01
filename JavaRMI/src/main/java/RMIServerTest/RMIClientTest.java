package RMIServerTest;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

//import static RMIServerTest.RMIServerTest.RMIAddress;

public class RMIClientTest {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        String RMIAddress = "rmi://" + "127.0.0.1" + ":" + 1099 + "/test";
        RMITestInterface rt = (RMITestInterface) Naming.lookup(RMIAddress);
        String result = rt.test();
        System.out.println(result);
    }
}
