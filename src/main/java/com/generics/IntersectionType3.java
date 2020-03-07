package com.generics;

import java.util.function.Consumer;
import java.util.function.Function;

public class IntersectionType3 {
    interface Pair<T> {
        T getFirst();
        T getSecond();
        void setFirst(T first);
        void setSecond(T second);
    }

    interface DelegateTo<T> {
        T delegate();
    }

    interface ForwardPair<T> extends DelegateTo<Pair<T>>, Pair<T> {

        @Override
        default T getFirst() {
            return delegate().getFirst();
        }

        @Override
        default T getSecond() {
            return delegate().getSecond();
        }

        @Override
        default void setFirst(T first) {
            delegate().setFirst(first);
        }

        @Override
        default void setSecond(T second) {
            delegate().setSecond(second);
        }
    }

    interface Convertable<T> extends DelegateTo<Pair<T>> {
        default void convet(Function<T, T> mapper) {
            Pair<T> pair = delegate();
            pair.setFirst(mapper.apply(pair.getFirst()));
            pair.setSecond(mapper.apply(pair.getSecond()));
        }
    }

    static class Name implements Pair<String> {
        String firstName;
        String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String getFirst() {
            return this.firstName;
        }

        @Override
        public String getSecond() {
            return this.lastName;
        }

        @Override
        public void setFirst(String first) {
            this.firstName = first;
        }

        @Override
        public void setSecond(String second) {
            this.lastName = second;
        }
    }

    public static void main(String[] args) {
        Pair<String> name = new Name("Jeong", "KeunJung");
        run((ForwardPair<String> & Convertable<String>) () -> name , o -> {
            print(o);
            o.convet(s -> s.toUpperCase());
            print(o);
        });
    }

    static <T> void print(Pair<T> pair) {
        System.out.println(pair.getFirst() + " " + pair.getSecond());
    }

    private static <T extends DelegateTo<S>, S> void run(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }
}
