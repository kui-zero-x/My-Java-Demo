package com.books.books.Effective_Java.Item_2.abstractBuilder;

import java.util.Objects;

public class Calzone extends Pizza {
    private final boolean isInside;

    public static class Builder extends Pizza.Builder<Builder> {

        private final boolean isInside;

        public Builder(boolean isInside) {
            this.isInside = Objects.requireNonNull(isInside);
        }

        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder) {
        super(builder);
        isInside = builder.isInside;
    }

    @Override
    public String toString() {
        return "Calzone{" +
                "isInside=" + isInside +
                ", toppings=" + toppings +
                '}';
    }

    public static void main(String[] args) {
        Calzone calzone = new Calzone.Builder(true).addTopping(Topping.DURAN_SAUCE).build();
        System.out.println(calzone);
    }
}
