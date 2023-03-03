package com.books.books.Effective_Java.Item_2.abstractBuilder;

import java.util.EnumSet;
import java.util.Set;

/**
 * 用于测试抽象类的 Builder（构建器）
 */
public abstract class Pizza {

    public enum Topping {KETCHUP, DURAN_SAUCE, MEAT_SAUCE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }

}
