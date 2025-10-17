package com.example.jsonView.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(setterPrefix = "with")
@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity {
}
