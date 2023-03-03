package com.books.books.Effective_Java.Item_2.builder;

/**
 * 用于测试普通类的 Builder（构建器）
 */
public class Daily {

    // necessary
    private final String eat;
    private final String drink;
    private final String sleep;
    // unnecessary
    private final String play;
    private final String study;

    public static class Builder {
        // necessary
        private final String eat;
        private final String drink;
        private final String sleep;
        // unnecessary
        private String play = "hongjing";
        private String study = "math";

        public Builder(String eat, String drink, String sleep) {
            this.eat = eat;
            this.drink = drink;
            this.sleep = sleep;
        }

        public Builder Play(String play) {
            this.play = play;
            return this;
        }

        public Builder study(String study) {
            this.study = study;
            return this;
        }

        public Daily build() {
            return new Daily(this);
        }
    }

    private Daily(Builder builder) {
        this.eat = builder.eat;
        this.drink = builder.drink;
        this.sleep = builder.sleep;
        this.play = builder.play;
        this.study = builder.study;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "eat='" + eat + '\'' +
                ", drink='" + drink + '\'' +
                ", sleep='" + sleep + '\'' +
                ", play='" + play + '\'' +
                ", study='" + study + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Daily daily = new Daily.Builder("rice", "water", "8th").Play("lol").study("DA").build();
        System.out.println(daily);
    }
}
