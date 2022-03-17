import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import com.sun.jndi.ldap.Obj;
import javax.naming.Reference;
import com.sun.jndi.rmi.registry.RegistryContext;

public class MAIN {
    public static void main(String[] args) throws NamingException {
//        Runtime.getRuntime()
        Context context = new InitialContext();
        context.lookup("ldap://127.0.0.1:1389/lnhjwt");
        context.close();
    }
}
