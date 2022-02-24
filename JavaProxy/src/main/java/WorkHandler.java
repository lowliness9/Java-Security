import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WorkHandler implements InvocationHandler {

    private Object obj;

    public WorkHandler() {
        //
    }

    public WorkHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前加入代码执行");
        Object invoke = method.invoke(obj,args);
        System.out.println("调用后加入代码执行");
        return invoke;
    }
}
