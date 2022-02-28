import java.io.*;
import java.lang.reflect.Method;


public class CustomClassLoader extends ClassLoader {

    public static final String className = "HelloWorld";

    public static byte[] classbytes = new byte[]{};

    byte[] cbytes;

    public String classFileName;

    public CustomClassLoader(){

    }

    public CustomClassLoader(String classFileName){
        this.classFileName = classFileName;
    }


    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            cbytes = this.loadClassData();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(cbytes.length);

        if(name.equals(className)){
            return defineClass(name,classbytes,0,classbytes.length);
        }
        return super.findClass(name);

    }

    public byte[] loadClassData() throws IOException {
        FileInputStream fis = null;
        byte[] classBytes = new byte[0];
        fis = new FileInputStream(this.classFileName);
        int length = fis.available();
        classBytes = new byte[length];
        fis.read(classBytes);
        fis.close();
        return classBytes;
    }


    public static void main(String[] args) throws Exception {
        String className = "HelloWorld";
        String classFileName = "HelloWorld.class";
        CustomClassLoader customClassLoader = new CustomClassLoader(classFileName);
        Class helloClass = customClassLoader.loadClass(className);
        Object testInstance = helloClass.newInstance();
        Method method = testInstance.getClass().getMethod("sayHello");
        method.invoke(testInstance);
    }
}
