import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class proxyTestMain {
    public static void main(String[] args) {
        People people = new Teacher();
        InvocationHandler invocationHandler = new WorkHandler(people);
        People proxy = (People) Proxy.newProxyInstance(
                invocationHandler.getClass().getClassLoader(),
                people.getClass().getInterfaces(),
                invocationHandler
        );
        System.out.println(proxy.work());

    }
}
