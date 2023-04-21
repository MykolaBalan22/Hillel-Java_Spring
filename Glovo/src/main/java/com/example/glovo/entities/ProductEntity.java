package com.example.glovo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@Table("products")
public class ProductEntity {
    @Id
    private int id;
    private String name;
    private double cost ;
}
