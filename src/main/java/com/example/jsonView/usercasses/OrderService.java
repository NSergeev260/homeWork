package com.example.jsonView.usercasses;

import com.example.jsonView.usercasses.dto.OrderStatus;
import com.example.jsonView.usercasses.dto.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderService insertOrder(UUID orderId, List<Product> productInfo, OrderStatus statusOrder );

    OrderService getOrder(UUID orderId);

    OrderService updateOrder(UUID orderId);

    void deleteOrder(UUID orderId);
}
