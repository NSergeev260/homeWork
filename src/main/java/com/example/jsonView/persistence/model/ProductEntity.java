package com.example.jsonView.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder(setterPrefix = "with")
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductEntity {

    @Column(name = "name_product")
    String name;

    @Column(name = "cost_product")
    String cost;
}
