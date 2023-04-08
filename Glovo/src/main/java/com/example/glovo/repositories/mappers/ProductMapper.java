package com.example.glovo.repositories.mappers;

import com.example.glovo.models.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getInt(1))
                .name(rs.getString(2))
                .cost(rs.getDouble(3))
                .build();
    }
}
