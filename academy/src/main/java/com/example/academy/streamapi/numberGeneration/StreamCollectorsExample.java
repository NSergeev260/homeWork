package com.example.academy.streamapi.numberGeneration;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        System.out.println("=== Группируйте заказы по продуктам ===");
        Map<String, List<Order>> groupingByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct));

        groupingByProduct.forEach((product, orderList) -> {
            System.out.println("Product: " + product + " |Count of product: " + orderList.size());
            orderList.forEach(order -> System.out.println("  - Cost: " + order.getCost()));
        });

        System.out.println();
        System.out.println("=== Для каждого продукта найдите общую стоимость всех заказов ===");
        Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                ));

        totalCostByProduct.forEach((product, total) -> System.out.println(product + ": " + total));

        System.out.println();
        System.out.println("=== Отсортируйте продукты по убыванию общей стоимости ===");
        totalCostByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        System.out.println();
        System.out.println("=== Выберите три самых дорогих продукта ===");
        List<Order> threeExpensiveOrders = orders.stream()
                .sorted(Comparator.comparingDouble(Order::getCost).reversed())
                .limit(3)
                .toList();

        threeExpensiveOrders.forEach(order -> System.out.println(order.getProduct() + ": " + order.getCost()));

        System.out.println();
        System.out.println("=== список трех самых дорогих продуктов и их общая стоимость ===");
        double sum = threeExpensiveOrders.stream()
                .mapToDouble(Order::getCost)
                .sum();
        threeExpensiveOrders.forEach(order -> System.out.println(order.getProduct() + ": " + order.getCost()));
        System.out.println("Total cost: " + sum);
    }
}

//  Практическое задание - Stream API - генерация чисел
//  Предположим, у нас есть список заказов,
//  и каждый заказ представляет собой продукт и его стоимость.
//  Задача состоит в использовании Stream API и коллекторов для решения следующих задач:
//
//  Создайте список заказов с разными продуктами и их стоимостями.
//  Группируйте заказы по продуктам.
//  Для каждого продукта найдите общую стоимость всех заказов.
//  Отсортируйте продукты по убыванию общей стоимости.
//  Выберите три самых дорогих продукта.
//  Выведите результат: список трех самых дорогих продуктов и их общая стоимость.