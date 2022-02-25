import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
    private static final long SerialversionUID = 1L;
    private String name = "张三";

    {
        System.out.println("{}无参数构造方法被调用");
    }

    static {
        System.out.println("static{}无参数构造方法被调用");
    }

    public User() {
        System.out.println("Object()无参数构造方法被调用");
        this.name = "lisi";
    }

    public String getName(){
        System.out.println("getter getName被调用");
        return name;
    }

    public void setName(String name){
        System.out.println("setter setName被调用");
        this.name = name;
    }

    public static void userprint(){
        System.out.println("静态方法被调用");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        //将名字反转写入二进制流
        String Name ="zhangsan";
        out.writeObject(new StringBuffer(Name).reverse());
        System.out.println("writeObject被调用");
//        out.writeInt(age);
    }


     private void readObject(ObjectInputStream ins) throws IOException,ClassNotFoundException{
        //将读出的字符串反转恢复回来
        String Name = ((StringBuffer)ins.readObject()).reverse().toString();
         System.out.println("readObject被调用");

    }

    @Override
    public String toString(){
        System.out.println("toString被调用");
        return "toString Test" + this.name;
    }
}
