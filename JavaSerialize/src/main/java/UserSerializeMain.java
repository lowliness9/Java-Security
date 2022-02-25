import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class UserSerializeMain {


    public static void main(String[] args) throws Exception{
        UserSerializeMain.serialize();
        UserSerializeMain.unserialize();
        HashMap<String,String> hs = new HashMap<String,String>();


    }

    /*对User对象进行序列化*/
    public static void serialize() throws Exception {
        System.out.println("序列化开始\n-----");
        FileOutputStream fout = new FileOutputStream("user.ser");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(new User());
        out.close();
        fout.close();
        System.out.println("-----\n序列化完成\n");

    }

    public static void unserialize() throws Exception {
        System.out.println("反序列化开始\n-----");
        FileInputStream fileInputStream = new FileInputStream("user.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User user = (User) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        System.out.println("-----\n反序列化完成\n");
        System.out.println(user.getName());
    }
}
