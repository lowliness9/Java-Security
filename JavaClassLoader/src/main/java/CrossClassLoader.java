import java.io.FileInputStream;
import java.io.IOException;
import com.sun.org.apache.bcel.internal.util.*;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

public class CrossClassLoader {

    public static class ClassLoaderA extends ClassLoader {
        public ClassLoaderA(ClassLoader parent){
            super(parent);
        }

        {
            try {
                defineClass("HelloWorld",loadClassData(),0,loadClassData().length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ClassLoaderB extends ClassLoader {
        public ClassLoaderB(ClassLoader parent){
            super(parent);
        }

        {
            try {
                defineClass("HelloWorld",loadClassData(),0,loadClassData().length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] loadClassData() throws IOException {
        FileInputStream fis = null;
        byte[] classBytes = new byte[0];
        fis = new FileInputStream("HelloWorld.class");
        int length = fis.available();
        classBytes = new byte[length];
        fis.read(classBytes);
        fis.close();
        return classBytes;
    }


    public static void main(String[] args) throws ClassNotFoundException {
        //父类加载器
        ClassLoader parentClassLoader = ClassLoader.getSystemClassLoader();
        //ClassLoaderA
        ClassLoaderA classLoaderA = new ClassLoaderA(parentClassLoader);
        //ClassLoaderB
        ClassLoaderB classLoaderB = new ClassLoaderB(parentClassLoader);

        Class<?> classA = Class.forName("HelloWorld",true,classLoaderA);

        Class<?> classB = Class.forName("HelloWorld",true,classLoaderB);

        System.out.println("classA == classB : " + (classA == classB));
    }
}

