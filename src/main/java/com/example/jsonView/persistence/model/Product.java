package com.example.jsonView.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Embeddable
@Builder(setterPrefix = "with")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_cost", precision = 19, scale = 2)
    BigDecimal productCost;
}
