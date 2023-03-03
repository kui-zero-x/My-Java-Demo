package com.books.books.Effective_Java.Item_3;

public class Singleton_2 {

    private static final Singleton_2 SINGLETON = new Singleton_2("刘晨光", "23");

    private String name;

    private String age;

    private Singleton_2(String name, String age) {
        if (SINGLETON != null) throw new RuntimeException("please use Singleton_1.SINGLETON!!");
        this.name = name;
        this.age = age;
    }

    public static Singleton_2 getInstance() {
        return SINGLETON;
    }

    @Override
    public String toString() {
        return "Singleton_2{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    // 在反序列化返回新对象时进行拦截
    private Object readResolve() {
        System.out.println("readResolve");
        return SINGLETON;
    }

    public static void main(String[] args) {
        Singleton_2 singleton = Singleton_2.getInstance();
        System.out.println(singleton);
    }
}
