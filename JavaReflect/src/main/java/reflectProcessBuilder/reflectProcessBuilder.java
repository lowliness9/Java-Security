package reflectProcessBuilder;

import java.util.Arrays;
import java.util.List;

public class reflectProcessBuilder {
    public static void main(String[] args) throws Exception {

//        reflectProcessBuilder.reflectGetConstructorDirect();
//        reflectProcessBuilder.reflectGetConstructorInvoke();
        reflectProcessBuilder.reflectGetConstructorLangInvoke();
    }


    //用强制类型转换进行执行，如果上下文没有ProcessBuilder类这个方法是不行的，还是得用调用
    public static void reflectGetConstructorDirect() throws Exception {
        Class clazz = Class.forName("java.lang.ProcessBuilder");
        //使用 getConstructor 调用有参构造方法
        ((ProcessBuilder) clazz.getConstructor(List.class).newInstance(Arrays.asList("open","/Applications/Umeeting.app/"))).start();
    }

    public static void reflectGetConstructorInvoke() throws Exception {
        Class clazz = Class.forName("java.lang.ProcessBuilder");
        clazz.getMethod("start").invoke(clazz.getConstructor(List.class).
                newInstance(Arrays.asList("open","/Applications/Umeeting.app/")));
    }

    public static void reflectGetConstructorLangInvoke() throws Exception {
        Class clazz = Class.forName("java.lang.ProcessBuilder");
        clazz.getMethod("start").invoke(clazz.getConstructor(String[].class).
                newInstance(new String[][]{{"whoami"}}));
    }

    public static void invokeGetConstructorLangArgs() throws Exception {
        Class clazz = Class.forName("java.lang.ProcessBuilder");
        ((ProcessBuilder)clazz.getConstructor(String[].class).newInstance(new String[][]{{"calc.exe"}})).start();
    }
}
