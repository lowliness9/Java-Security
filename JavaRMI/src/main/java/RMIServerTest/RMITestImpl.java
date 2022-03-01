package RMIServerTest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMITestImpl extends UnicastRemoteObject implements RMITestInterface {

    public static final long serialVersionUID = 1L;

    protected RMITestImpl() throws RemoteException {
        super();
    }

    @Override
    public String test() throws RemoteException {
        try {
            Runtime.getRuntime().exec("touch /tmp/success_tmp");
        }catch (Exception e){
            System.out.println("error");
        }
        return "HelloWorld,Are you OK ?";
    }
}
