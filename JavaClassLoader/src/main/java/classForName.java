public class classForName {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        /*
        * 初始静态块
        * */
        Class c = Class.forName("HelloWorld");
        Thread.sleep(5000);
//        this.getClass().getClassLoader().loadClass("HelloWorld");
        Class d = ClassLoader.getSystemClassLoader().loadClass("HelloWorld");

    }
}
