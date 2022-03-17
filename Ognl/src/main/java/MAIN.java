import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.HashMap;
import java.util.Map;

public class MAIN {
    public static void main(String[] args) throws OgnlException {
        User mainUser = new User("dageda",40,180);
        User user1 = new User("xiaoming",23,176);
        User user2 = new User("xiaohong",24,168);
        Map<String,User> context = new HashMap<String,User>();
        context.put("user1",user1);
        context.put("user2",user2);

        OgnlContext ognlContext = new OgnlContext();
        ognlContext.setRoot(mainUser);
        ognlContext.setValues(context);


        //从Root中取值
        String name = (String) Ognl.getValue("name",ognlContext,ognlContext.getRoot());
        int age = (int) Ognl.getValue("age",ognlContext,ognlContext.getRoot());
        System.out.println("从Root中取出User对象的name属性：" + name);
        System.out.println("从Root中取出User对象的age属性：" + age);

        //从Context中取出对象的属性值
        String user1_name = (String) Ognl.getValue("#user1.name",ognlContext,ognlContext.getRoot());
        int user1_age = (int) Ognl.getValue("#user1.age",ognlContext,ognlContext.getRoot());
        System.out.println("从Context中取出User1对象的name属性：" + user1_name);
        System.out.println("从Context中取出User1对象的age属性：" + user1_age);

        String user2_name = (String) Ognl.getValue("#user2.name",ognlContext,ognlContext.getRoot());
        int user2_age = (int) Ognl.getValue("#user2.age",ognlContext,ognlContext.getRoot());
        System.out.println("从Context中取出User2对象的name属性：" + user2_name);
        System.out.println("从Context中取出User2对象的age属性：" + user2_age);

        //为Root中的User对象属性赋值
        Ognl.getValue("name='dagedaDD'",ognlContext,ognlContext.getRoot());
        String rootNewName = (String) Ognl.getValue("name",ognlContext,ognlContext.getRoot());
        System.out.println("Root对象中User对象name改变："+ rootNewName);

        //为Context中的User对象属性赋值
        Ognl.getValue("#user1.height='188'",ognlContext,ognlContext.getRoot());
        int user1Height = (int) Ognl.getValue("#user1.height",ognlContext,ognlContext.getRoot());
        System.out.println("Context中User1对象height改变："+ user1Height);

        //Ognl调用对象的方法
        int lg = (int) Ognl.getValue("'helloworld'.length()",ognlContext);
        String gName = (String) Ognl.getValue("#user1.getName()",ognlContext,ognlContext.getRoot());
        System.out.println(lg);
        System.out.println(gName);

        //Ognl支持类的静态方法调用和值得访问
        Object obj = Ognl.getValue("@java.lang.String@format('foo %s','bar')",ognlContext);
        System.out.println(obj);

        //Ognl调用java.lang.Runtime执行命令
        Object calc = Ognl.getValue("@java.lang.Runtime@getRuntime().exec('open -a calculator.app')",ognlContext);



    }
}
