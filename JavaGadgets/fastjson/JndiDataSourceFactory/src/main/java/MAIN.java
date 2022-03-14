import org.apache.ibatis.datasource.jndi.JndiDataSourceFactory;

import java.util.Properties;

public class MAIN {

    public static void main(String[] args) {

        JndiDataSourceFactory jndiDataSourceFactory = new JndiDataSourceFactory();
        Properties properties = new Properties();
        properties.put("data_source","ldap://127.0.0.1:1389/ivvhnu");
        jndiDataSourceFactory.setProperties(properties);

    }
}
