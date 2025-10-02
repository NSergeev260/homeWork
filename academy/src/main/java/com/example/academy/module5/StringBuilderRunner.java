package com.example.academy.module5;

import com.example.academy.module5.undoStringBuilder.UndoStringBuilder;

public class StringBuilderRunner {

    public static void main(String[] args) {
        UndoStringBuilder strBuilder = new UndoStringBuilder();
        strBuilder.append("hello").append("_").append("world");
        System.out.println("FULL text: " + strBuilder);
        strBuilder.undo();
        System.out.println("First undo: " + strBuilder);
        strBuilder.undo();
        System.out.println("Second undo: " + strBuilder);
        strBuilder.undo();
        System.out.println("Third undo: " + strBuilder);
        strBuilder.undo();
    }
}

// Практическое задание - StringBuilder
// Изучите внутреннюю реализацию класса StringBuilder и напишите свою с
// добавлением дополнительного метода - undo().

// Прежде чем приступать - прочитайте про паттерн snapshot и примените его в
// своей реализации.

// примечание: полностью переписывать все методы которые есть
// в StringBuilder не нужно, в задании важно именно понимание сути паттерна.
// В случае, если задание остаётся непонятным, задайте вопрос ментору
// https://github.com/NSergeev260/homeWork.git