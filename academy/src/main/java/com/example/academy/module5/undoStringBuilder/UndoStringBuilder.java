package com.example.academy.module5.undoStringBuilder;

import com.example.academy.module5.history.StringBuilderHistory;
import com.example.academy.module5.patternsnap.StringBuilderSnap;

public class UndoStringBuilder {

    private StringBuilder stringBuilder;
    private StringBuilderHistory stringHistory;

    public UndoStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.stringHistory = new StringBuilderHistory();
    }

    public UndoStringBuilder(String str) {
        this.stringBuilder = new StringBuilder(str);
        this.stringHistory = new StringBuilderHistory();
    }

    private void saveState() {
        stringHistory.save(new StringBuilderSnap(stringBuilder.toString()));
    }

    private void restoreState(StringBuilderSnap snap) {
        this.stringBuilder = new StringBuilder(snap.getState());
    }

    public UndoStringBuilder append(String str) {
        saveState();
        stringBuilder.append(str);
        return this;
    }

    public UndoStringBuilder undo() {
        if (stringHistory.isCanUndo()) {
            StringBuilderSnap stringBuilderSnap = stringHistory.undo();
            if (stringBuilderSnap != null) {
                restoreState(stringBuilderSnap);
            }
        } else {
            System.out.println("Can not UNDO the operation");
        }

        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
