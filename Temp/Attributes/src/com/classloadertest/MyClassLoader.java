package com.classloadertest;

public class MyClassLoader extends ClassLoader{

    public String className;
    public byte[] classByte2;


    public MyClassLoader(String className,byte[] classByte){
        super();
        this.className = className;
        this.classByte2 = classByte;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        if(name.equals(this.className)){
            System.out.println(1);
            return defineClass(this.className,this.classByte2,0,this.classByte2.length);
        }
        System.out.println(2);
        return super.findClass(name);
    }
}
