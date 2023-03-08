package com.demo.myjavademo.demo.compare;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Grades implements Comparable<Grades> {

    private Integer chinese;

    private Integer english;

    private Integer math;

    private static final Comparator<Grades> COMPARATOR = Comparator.comparingInt(Grades::getChinese)
            .thenComparingInt(Grades::getEnglish)
            .thenComparingInt(Grades::getMath);

    public Grades(Integer chinese, Integer english, Integer math) {
        this.chinese = chinese;
        this.english = english;
        this.math = math;
    }

    public Grades() {
    }

    public Integer getChinese() {
        return chinese;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    public static Comparator<Grades> getCOMPARATOR() {
        return COMPARATOR;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "chinese=" + chinese +
                ", english=" + english +
                ", math=" + math +
                '}';
    }


    @Override
    public int compareTo(Grades o) {
        return COMPARATOR.compare(this, o);
    }

    public static void main(String[] args) {
        Random random = new Random();
        Grades[] grades = new Grades[10];
        for (int i = 0; i < 10; i++) {
            grades[i] = new Grades(random.nextInt(101), random.nextInt(101), random.nextInt(101));
        }
        System.out.println(Arrays.toString(grades));
        Arrays.sort(grades);
        System.out.println(Arrays.toString(grades));
    }
}
