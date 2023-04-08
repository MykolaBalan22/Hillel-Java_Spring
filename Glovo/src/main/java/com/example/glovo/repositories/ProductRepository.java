package com.example.glovo.repositories;

import com.example.glovo.models.Product;
import com.example.glovo.repositories.mappers.OrderMapper;
import com.example.glovo.repositories.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Product> getProductsByCertainOrder(int orderId){
        String getQuery = """
                SELECT products.id ,products.name ,products.cost  FROM products 
                inner join order_products on products.id=order_products.prod_id
                 where order_products.order_id=
                """;
        return jdbcTemplate.query(getQuery+orderId ,new ProductMapper());
    }
}
