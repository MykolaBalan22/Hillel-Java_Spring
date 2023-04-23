package com.example.glovo.entities.converters;

import com.example.glovo.entities.ProductEntity;
import com.example.glovo.models.Product;

import java.time.LocalDate;

public class ProductEntityConverter {
    public static Product productEntityToProduct(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cost(entity.getCost())
                .dateOfPull(LocalDate.now())
                .build();
    }
    public static ProductEntity productToProductEntity(Product product){
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .cost(product.getCost())
                .build();
    }
}
