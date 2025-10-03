package com.example.academy.collection.count;

import java.util.*;

public class CountRunner {
    public static void main(String[] args) {
        List<Integer> intArray = Arrays.asList(0, 1, 2, 3, 4, 5, 1, 1, 1, 1, 5, 5, 5, 5);
        countOfElements(intArray);
        List<String> stringArray = Arrays.asList("a", "b", "b", "a");
        countOfElements(stringArray);
    }

    public static <T> Map<T, Integer> countOfElements(List<T> array) {
        Map<T, Integer> mapOfElements = new HashMap<>();

        for (T key : array) {
            mapOfElements.put(key, mapOfElements.getOrDefault(key, 0) + 1);
        }

        System.out.println("Map of count elements: {value = number of repetitions}");
        System.out.println(mapOfElements);

        return mapOfElements;
    }
}

//  Практическое задание - Collection - count of elements
//  Напишите метод, который получает на вход массив элементов
//  и возвращает Map, ключи в котором - элементы,
//  а значения - сколько раз встретился этот элемент