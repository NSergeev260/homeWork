package com.example.academy.collection.filter;

import com.example.academy.collection.filter.functioninterface.Filter;
import com.example.academy.collection.filter.impl.IntegerFilterImpl;
import com.example.academy.collection.filter.impl.StringFilterImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterRunner {
    public static void main(String[] args) {
        List<String> stringArray = Arrays.asList("Hello", "WORLD", "developer");
        StringFilterImpl stringFilter = new StringFilterImpl();
        List<String> modStringArray = filter(stringArray, stringFilter);
        System.out.println("Raw array: "+ stringArray);
        System.out.println("Mod array: " + modStringArray);

        List<Integer> intArray = Arrays.asList(1, 2, 3, 4, 5, 6);
        IntegerFilterImpl intFilter = new IntegerFilterImpl();
        List<Integer> modIntArray = filter(intArray, intFilter);
        System.out.println("Raw array: "+ intArray);
        System.out.println("Mod array: " + modIntArray);
    }

    public static <T> List<T> filter(List<T> array, Filter<T> filter) {
        List<T> filteredArray = new ArrayList<>();

        for (T value : array) {
            T filteredValue = filter.apply(value);
            filteredArray.add(filteredValue);
        }
        return filteredArray;
    }
}

//  Практическое задание - Collection - фильтрация
//  Напишите метод filter,
//  который принимает на вход массив любого типа,
//  вторым арументом метод должен принимать класс,
//  реализующий интерфейс Filter,
//  в котором один метод - T apply(T o) (параметризованный).
//
//  Метод должен быть реализован так чтобы
//  возвращать новый массив,
//  к каждому элементу которого была применена функция apply
