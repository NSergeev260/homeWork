package com.example.academy.collection.filter.impl;

import com.example.academy.collection.filter.functioninterface.Filter;

public class IntegerFilterImpl implements Filter<Integer> {

    @Override
    public Integer apply(Integer x) {
        return x + 1;
    }
}
