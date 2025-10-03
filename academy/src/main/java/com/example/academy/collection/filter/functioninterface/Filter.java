package com.example.academy.collection.filter.functioninterface;

@FunctionalInterface
public interface Filter<T> {
    T apply(T o);
}
