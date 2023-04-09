package com.example.glovo.repositories.mappers;

import com.example.glovo.models.Order;
import com.example.glovo.models.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderWithProductsMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Product> product = new ArrayList<>();
        product.add(Product.builder()
                .id(rs.getInt(4))
                .name(rs.getString(5))
                .cost(rs.getDouble(6))
                .build());
        return Order.builder()
                .id(rs.getInt(1))
                .date(rs.getDate(2).toLocalDate())
                .cost(rs.getDouble(3))
                .products(product)
                .build();
    }
}
