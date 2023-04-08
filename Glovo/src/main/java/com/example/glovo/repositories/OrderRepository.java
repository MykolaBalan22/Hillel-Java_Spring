package com.example.glovo.repositories;

import com.example.glovo.models.Order;
import com.example.glovo.repositories.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Order getOrder(int id){
       return jdbcTemplate.queryForObject("SELECT *  FROM orders WHERE id="+id ,new OrderMapper());
    }
}
