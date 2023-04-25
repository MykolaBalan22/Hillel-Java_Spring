package com.example.glovo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Table("order_products")
public class OrderWithProductEntity {
    @Id
    private int orderId;

    private int prodId;
    private LocalDate createdDate;
}
