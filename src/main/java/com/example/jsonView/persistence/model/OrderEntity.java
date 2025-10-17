package com.example.jsonView.persistence.model;

import com.example.jsonView.usercasses.dto.OrderStatus;
import com.example.jsonView.usercasses.dto.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@Setter
@Getter
@Entity
@Table(name = "order")
public class OrderEntity {

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "product_info")
    private List<Product> productInfo;

    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    @Column(name = "status_order")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}