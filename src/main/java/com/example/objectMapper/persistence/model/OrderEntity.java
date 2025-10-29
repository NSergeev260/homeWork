package com.example.objectMapper.persistence.model;

import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import com.example.objectMapper.usercasses.dto.OrderStatus;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    UUID orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "customer", nullable = false)
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @Column(name = "products", nullable = false)
    @JoinColumn(name = "product_id")
    @Builder.Default
    List<ProductEntity> products = new ArrayList<>();

    @Column(name = "order_date", nullable = false)
    LocalDateTime orderDate;

    @Column(name = "shipping_address", nullable = false)
    String shippingAddress;

    @Column(name = "total_price", nullable = false)
    BigDecimal totalPrice;

    @Column(name = "order_status", nullable = false)
    OrderStatus orderStatus;
}
