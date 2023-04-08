package com.example.glovo.repositories;

import com.example.glovo.models.Order;
import com.example.glovo.repositories.mappers.OrderMapper;
import com.example.glovo.repositories.mappers.OrderWithProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Order> getAllWithProduct(){
        String getAllQuery = """
                SELECT orders.id ,orders.date ,orders.cost ,products.id ,products.name ,products.cost  FROM orders 
                inner join order_products on orders.id=order_products.order_id 
                inner join products on prod_id=products.id;
                """;
        return jdbcTemplate.query(getAllQuery ,new OrderWithProductsMapper());
    }
}
