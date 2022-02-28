import java.io.IOException;

/*
* tar cvf CMD.jar CMD.class
* */
public class CMD {
    public static Process exec(String cmd) throws IOException {
        return Runtime.getRuntime().exec(cmd);
    }
}
