package com.books.books.Effective_Java.Item_3;

import java.io.*;

public class Singleton_1 implements Serializable {

    public static final Singleton_1 SINGLETON = new Singleton_1("刘晨光", "23");

    private transient String name;

    private transient String age;

    private Singleton_1(String name, String age) {
        if (SINGLETON != null) throw new RuntimeException("please use Singleton_1.SINGLETON!!");
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Singleton_1{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    // 在反序列化返回新对象时进行拦截
    private Object readResolve() {
        System.out.println("readResolve");
        return SINGLETON;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton_1 singleton = Singleton_1.SINGLETON;
        System.out.println(singleton);
//        ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("D:\\file.txt"));
//        write.writeObject(SINGLETON);
//        ObjectInputStream read = new ObjectInputStream(new FileInputStream("D:\\file.txt"));
//        Singleton_1 singleton_1 = (Singleton_1)read.readObject();
//        System.out.println(singleton_1);
//        System.out.println(singleton_1 == SINGLETON);
    }
}
