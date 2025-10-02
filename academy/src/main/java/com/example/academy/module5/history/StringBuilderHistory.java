package com.example.academy.module5.history;

import com.example.academy.module5.patternsnap.StringBuilderSnap;
import java.util.ArrayDeque;
import java.util.Deque;

public class StringBuilderHistory {

    private final Deque<StringBuilderSnap> stringHistory = new ArrayDeque<>();

    public void save(StringBuilderSnap stringSnap) {
        stringHistory.addLast(stringSnap);
    }

    public StringBuilderSnap undo() {
        if (stringHistory.size() >= 1) {
            return stringHistory.removeLast();
        }

        return null;
    }

    public boolean isCanUndo() {
        if (stringHistory.isEmpty()) {
            System.out.println("Can not UNDO the operation");
        }
        return true;
    }
}
