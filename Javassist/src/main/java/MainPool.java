//import javassist.;
import javassist.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainPool {
    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        //ClassPool
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("PeopleObject");
//        pool.makeClass("11");

        //CtMethod
        CtMethod ctMethod = ctClass.getDeclaredMethod("say",new CtClass[]{pool.get(String.class.getName())});
        String before = "System.out.println(\"insert before method\");";
        ctMethod.insertBefore(before);

        //CtField
        CtField nameField = ctClass.getDeclaredField("name");
        CtField heightField = new CtField(CtClass.intType,"height",ctClass);
        heightField.setModifiers(Modifier.PUBLIC);
        ctClass.addField(heightField,"180");
        System.out.println(nameField);

        //CtConstructor
        CtConstructor ctConstructor= ctClass.getDeclaredConstructor(new CtClass[]{});
        CtConstructor ctConstructorNew = CtNewConstructor.make("public PeopleObject(String name){}", ctClass);
        ctClass.addConstructor(ctConstructorNew);


        //classpath
            // 将classpath插入到指定classpath之前
//        pool.insertClassPath(new ClassClassPath(this.getClass()));
            // 将classpath添加到指定classpath之后
//        pool.appendClassPath(new ClassClassPath(this.getClass()));
            // 将一个目录作为classpath
        pool.insertClassPath("/xxx/lib");
        //make to class
        ctClass.toClass();
    }
}
