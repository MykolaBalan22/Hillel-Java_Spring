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
@Table("orders")
public class OrderEntity {
    @Id
    private int id;
    private LocalDate date;
    private double cost;
}
