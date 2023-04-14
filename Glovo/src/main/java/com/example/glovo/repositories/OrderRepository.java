package com.example.glovo.repositories;

import com.example.glovo.models.Order;
import com.example.glovo.repositories.mappers.OrderMapper;
import com.example.glovo.repositories.mappers.OrderWithProductsMapper;
import com.example.glovo.repositories.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class OrderRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Order getOrder(int id) {
        return jdbcTemplate.queryForObject("SELECT *  FROM orders WHERE id=" + id, new OrderMapper());
    }

    public List<Order> getAllWithProduct() {
        String getAllQuery = """
                SELECT orders.id ,orders.date ,orders.cost ,products.id ,products.name ,products.cost  FROM orders 
                inner join order_products on orders.id=order_products.order_id 
                inner join products on prod_id=products.id;
                """;
        return jdbcTemplate.query(getAllQuery, new OrderWithProductsMapper());
    }


    public Order add(Order order) {
        jdbcTemplate.execute("INSERT INTO orders value(" + order.getId() + ", \'" + order.getDate() + "\'," + order.getCost() + ")");
        try {
            return getOrder(order.getId());
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found", ex);
        }
    }
}
