import java.io.IOException;

public class HelloWorld {

    static {
        System.out.println("I am static...");
    }

    public void sayHello() throws IOException {
        System.out.println("Hello, I am Hello World");
        Runtime.getRuntime().exec("open -a calculator");
    }
}

