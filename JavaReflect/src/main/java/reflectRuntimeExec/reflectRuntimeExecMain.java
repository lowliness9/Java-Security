package reflectRuntimeExec;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class reflectRuntimeExecMain {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
//        反射调用方法实现命令执行
        reflectRuntimeExecMain.ReflectRuntimeByMethod();
//        反射调用构造方法实现命令执行
        reflectRuntimeExecMain.ReflectRuntimeByConstructor();
    }

    public static void ReflectRuntimeByConstructor() throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {

        Constructor m = Class.forName("java.lang.Runtime").getDeclaredConstructor();
        /*
        * setAccessible这个是必须的，我们在获取到一个私有方法后必须用setAccessible修改它的作用域，否则仍然不能调用。
        */
        m.setAccessible(true);
        Class.forName("java.lang.Runtime").getMethod("exec", String.class).
                invoke(m.newInstance(),"open /Applications/Umeeting.app/");
    }

    public static void ReflectRuntimeByMethod() throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /*
        * Runtime 类的构造方法是私有的，Runtime类就是单例模式，我们只能通过 Runtime.getRuntime() 来获取到 Runtime对象
        */
        Class clz = Class.forName("java.lang.Runtime");
        Method execM = clz.getMethod("exec", String.class);
        Method getRuntimeM = clz.getMethod("getRuntime");
        Object runtime = getRuntimeM.invoke(clz);
        execM.invoke(runtime,"open /Applications/Umeeting.app/");
    }
}
