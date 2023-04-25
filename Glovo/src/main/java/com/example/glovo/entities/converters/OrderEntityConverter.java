package com.example.glovo.entities.converters;

import com.example.glovo.entities.OrderEntity;
import com.example.glovo.entities.ProductEntity;
import com.example.glovo.models.Order;
import com.example.glovo.models.Product;

import java.time.LocalDate;
import java.util.List;

public class OrderEntityConverter {
    public static Order orderEntityToOrder(OrderEntity entity, List<ProductEntity> productEntities) {
        List<Product> products = productEntities.stream()
                .map(ProductEntityConverter::productEntityToProduct)
                .toList();
        return Order.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .cost(entity.getCost())
                .products(products)
                .build();
    }
    public static OrderEntity orderToOrderEntity(Order order){
        return OrderEntity.builder()
                .id(order.getId())
                .date(order.getDate())
                .cost(order.getCost())
                .build();
    }
}
