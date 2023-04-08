package com.example.glovo.repositories.mappers;

import com.example.glovo.models.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Order.builder()
                .id(rs.getInt(1))
                .date(rs.getDate(2))
                .cost(rs.getDouble(3))
                .build();
    }
}
