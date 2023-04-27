package com.demo.myjavademo.Demos.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.*;

/**
 * Kryo序列化工具测试
 */
public class kryoSerialize {
    static public void main (String[] args) throws Exception {
        Kryo kryo = new Kryo();
        kryo.register(SomeClass.class);

        SomeClass object = new SomeClass();
        object.value = "Hello Kryo!";

        //序列化
        Output output = new Output(new FileOutputStream("D:\\file.bin"));
        kryo.writeObject(output, object);
        output.close();

        //反序列化
        Input input = new Input(new FileInputStream("D:\\file.bin"));
        SomeClass object2 = kryo.readObject(input, SomeClass.class);
        input.close();
    }
    static public class SomeClass {
        String value;
    }
}
