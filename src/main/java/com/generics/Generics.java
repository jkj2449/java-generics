package com.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1 ,2, 3, 4, 5, 6, 7);
        System.out.println(max(list));

        reverse(list);
        System.out.println(list);
    }

    private static <T> void reverse(List<?> list) {
        List<?> temp = new ArrayList<>(list);
        List rawList = list;
        for (int i = 0; i < rawList.size(); i++) {
            rawList.set(i, temp.get(rawList.size() - i - 1));
        }

    }

    private static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        return list.stream().reduce((a,b) -> a.compareTo(b) > 0 ? a :b).get();
    }
}
