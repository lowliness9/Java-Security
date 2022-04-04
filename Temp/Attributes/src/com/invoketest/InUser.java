package com.invoketest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InUser implements UserImp {
    String namer = "xxxx";

    @Override
    public Object uimplet() {
        return null;
    }

    public String getNamer() {
        return namer;
    }

    public void setNamer(String namer) {
        this.namer = namer;
    }


    public void execReflectM() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Runtime.class.getDeclaredMethod("exec", String.class).invoke(Runtime.class.getDeclaredMethod("getRuntime", null).invoke(null), "calc");
    }

    public void execReflectC() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = Runtime.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Runtime.class.getDeclaredMethod("exec", String.class).invoke(constructor.newInstance(), "calc");
    }

}
