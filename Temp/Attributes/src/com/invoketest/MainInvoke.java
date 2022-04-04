package com.invoketest;

import java.lang.reflect.InvocationTargetException;

public class MainInvoke {


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        Class  cls = Class.forName("com.invoketest.InUser");
        Object inUser = cls.getDeclaredConstructors()[0].newInstance();

        Object setter = cls.getDeclaredMethod("setNamer", String.class).invoke(inUser,"xiaoming");

        String name = (String) cls.getDeclaredMethod("getNamer",null).invoke(inUser,null);
        System.out.println(name);

        cls.getDeclaredMethod("execReflectM",null).invoke(cls.newInstance(),null);


    }
}
