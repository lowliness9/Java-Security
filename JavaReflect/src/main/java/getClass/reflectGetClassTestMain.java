package getClass;

public class reflectGetClassTestMain {


    public static void main(String[] args) throws ClassNotFoundException {
        People people = new People(18,"小米");
        System.out.println("我是People对象的实例："+ people);
        System.out.println("-----");

        Class c1 = people.getClass();
        System.out.println("第一种方法获取Class：getClass()方法");
        System.out.println(c1);
        System.out.println("-----");

        Class c2 = Class.forName("getClass.People");
        System.out.println("第二种方法获取Class：forName()方法");
        System.out.println(c2);
        System.out.println("-----");

        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class c3 = loader.loadClass("getClass.People");
        System.out.println("第三种方法获取Class：ClassLoader.loadClass方法");
        System.out.println(c3);

    }
}
