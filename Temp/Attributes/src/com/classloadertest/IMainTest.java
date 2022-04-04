package com.classloadertest;

import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

public class IMainTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        /*
        package com.classloadertest;
        import java.lang.reflect.InvocationTargetException;

        public class EvilClass {
            public EvilClass(){
            }

        public void exec(String cmd) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Runtime.class.getDeclaredMethod("exec", String.class).invoke(Runtime.class.getDeclaredMethod("getRuntime", null).invoke(null), cmd);
        }
}
        */

        String EvilClassCls = "yv66vgAAADQALgoACAAcBwAdCAASBwAeBwAfCgAEACAIACEHACIKACMAJAcAJQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAfTGNvbS9jbGFzc2xvYWRlcnRlc3QvRXZpbENsYXNzOwEABGV4ZWMBABUoTGphdmEvbGFuZy9TdHJpbmc7KVYBAANjbWQBABJMamF2YS9sYW5nL1N0cmluZzsBAApFeGNlcHRpb25zBwAmBwAnBwAoAQAKU291cmNlRmlsZQEADkV2aWxDbGFzcy5qYXZhDAALAAwBABFqYXZhL2xhbmcvUnVudGltZQEAD2phdmEvbGFuZy9DbGFzcwEAEGphdmEvbGFuZy9TdHJpbmcMACkAKgEACmdldFJ1bnRpbWUBABBqYXZhL2xhbmcvT2JqZWN0BwArDAAsAC0BAB1jb20vY2xhc3Nsb2FkZXJ0ZXN0L0V2aWxDbGFzcwEAH2phdmEvbGFuZy9Ob1N1Y2hNZXRob2RFeGNlcHRpb24BACtqYXZhL2xhbmcvcmVmbGVjdC9JbnZvY2F0aW9uVGFyZ2V0RXhjZXB0aW9uAQAgamF2YS9sYW5nL0lsbGVnYWxBY2Nlc3NFeGNlcHRpb24BABFnZXREZWNsYXJlZE1ldGhvZAEAQChMamF2YS9sYW5nL1N0cmluZztbTGphdmEvbGFuZy9DbGFzczspTGphdmEvbGFuZy9yZWZsZWN0L01ldGhvZDsBABhqYXZhL2xhbmcvcmVmbGVjdC9NZXRob2QBAAZpbnZva2UBADkoTGphdmEvbGFuZy9PYmplY3Q7W0xqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsAIQAKAAgAAAAAAAIAAQALAAwAAQANAAAAMwABAAEAAAAFKrcAAbEAAAACAA4AAAAKAAIAAAAHAAQACQAPAAAADAABAAAABQAQABEAAAABABIAEwACAA0AAABlAAYAAgAAAC0SAhIDBL0ABFkDEgVTtgAGEgISBwG2AAYBA70ACLYACQS9AAhZAytTtgAJV7EAAAACAA4AAAAKAAIAAAAMACwADQAPAAAAFgACAAAALQAQABEAAAAAAC0AFAAVAAEAFgAAAAgAAwAXABgAGQABABoAAAACABs=";
        String className = "com.classloadertest.EvilClass";
        byte[] classByte = Base64.getDecoder().decode(EvilClassCls);


        MyClassLoader mcls = new MyClassLoader(className, classByte);
        Class EvilClass = mcls.loadClass(className);
        EvilClass.getDeclaredMethod("exec", String.class).invoke(EvilClass.newInstance(), "calc");


    }

}
