package com.example.academy.collection.filter.impl;

import com.example.academy.collection.filter.functioninterface.Filter;

public class StringFilterImpl implements Filter<String> {

    @Override
    public String apply(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                chars[i] = Character.toLowerCase(chars[i]);
            } else {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return new String(chars);
    }
}
