package com.example.DA.Algorithms.union_find;

import java.io.*;
import java.util.Random;

public class Test {

    @org.junit.Test
    public void TestQF() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\tenyUF.txt")));
        QuickFind quickFind = new QuickFind(Integer.parseInt(reader.readLine()));
        long t1 = System.currentTimeMillis();
        while (true) {
            String str = reader.readLine();
            if (str == null) break;
            int p = Integer.parseInt(str.substring(0, str.indexOf(" ")));
            int q = Integer.parseInt(str.substring(str.indexOf(" ") + 2));
            if (!quickFind.connected(p, q)) {
                quickFind.union(p, q);
                System.out.println("union: " + p + " " + q);
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("count: " + quickFind.count());
        System.out.println(t2 - t1);
    }

    @org.junit.Test
    public void TestQU() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\tenyUF.txt")));
        QuickUnion quickUnion = new QuickUnion(Integer.parseInt(reader.readLine()));
        long t1 = System.currentTimeMillis();
        while (true) {
            String str = reader.readLine();
            if (str == null) break;
            int p = Integer.parseInt(str.substring(0, str.indexOf(" ")));
            int q = Integer.parseInt(str.substring(str.indexOf(" ") + 2));
            if (!quickUnion.connected(p, q)) {
                quickUnion.union(p, q);
                System.out.println("union: " + p + " " + q);
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("count: " + quickUnion.count());
        System.out.println(t2 - t1);
    }

    @org.junit.Test
    public void TestQUP() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\tenyUF.txt")));
        QuickUnionPlus quickUnionPlus = new QuickUnionPlus(Integer.parseInt(reader.readLine()));
        long t1 = System.currentTimeMillis();
        while (true) {
            String str = reader.readLine();
            if (str == null) break;
            int p = Integer.parseInt(str.substring(0, str.indexOf(" ")));
            int q = Integer.parseInt(str.substring(str.indexOf(" ") + 2));
            if (!quickUnionPlus.connected(p, q)) {
                quickUnionPlus.union(p, q);
                System.out.println("union: " + p + " " + q);
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("count: " + quickUnionPlus.count());
        System.out.println(t2 - t1);
    }

    @org.junit.Test
    public void write() throws IOException {
        File file = new File("E:\\tenyUF.txt");
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
        Random random = new Random();
        FileOutputStream outputStream = new FileOutputStream("E:\\tenyUF.txt");
        PrintStream writer = new PrintStream(outputStream, true);
        writer.println(200000);
        for (int i = 0; i < 200000; i++) {
            writer.print(random.nextInt(200000));
            writer.print("  ");
            writer.print(random.nextInt(200000));
            writer.println();
        }
    }
}
