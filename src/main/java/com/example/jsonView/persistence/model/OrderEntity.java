package com.example.jsonView.persistence.model;

import com.example.jsonView.usercasses.dto.OrderStatus;
import com.example.jsonView.usercasses.dto.Product;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@Setter
@Getter
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;

    @ElementCollection
    @CollectionTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"))
//    @Column(name = "product_info")
    private List<Product> productInfo;

    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_order")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}