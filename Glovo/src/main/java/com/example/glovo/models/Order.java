package com.example.glovo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Order {
    private int id;
    private Date date;
    private double cost;
    private List<Product> products;
}
