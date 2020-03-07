package com.generics;

import java.util.function.Consumer;
import java.util.function.Function;

public class IntersectionType {
    interface Hello extends Function {
        default void hello() {
            System.out.println("hello");
        }
    }

    interface Hi extends Function {
        default void hi() {
            System.out.println("hi");
        }
    }

    public static void main(String[] args) {
        run((Function & Hello & Hi)s -> s, o -> {
            o.hello();
            o.hi();
        });
    }

    private static <T extends Function> void run (T t, Consumer<T> consumer) {
        consumer.accept(t);
    }
}
