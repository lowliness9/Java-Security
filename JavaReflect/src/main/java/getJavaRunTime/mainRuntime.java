package getJavaRunTime;

public class mainRuntime {
    public static void main(String[] args) throws ClassNotFoundException {

        Class c1 = java.lang.Runtime.class;
        System.out.println(c1);
        System.out.println("-----");

        Class c2 = Class.forName("java.lang.Runtime");
        System.out.println(c2);
        System.out.println("-----");

        Class c3 = ClassLoader.getSystemClassLoader().loadClass("java.lang.Runtime");
        System.out.println(c3);
        System.out.println("-----");
    }




}
