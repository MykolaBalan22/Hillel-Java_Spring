package com.example.glovo.repositories;

import com.example.glovo.models.Order;
import com.example.glovo.repositories.mappers.OrderMapper;
import com.example.glovo.repositories.mappers.OrderWithProductsMapper;
import com.example.glovo.repositories.statements.CountStatment;
import com.example.glovo.repositories.statements.DeleteStatement;
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
        try {
            return jdbcTemplate.queryForObject("SELECT *  FROM orders WHERE id=" + id, new OrderMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found", ex);
        }
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

    public boolean removeOrderWithProducts(int orderId) {
        int countInOrdersWithProducts = jdbcTemplate.execute("SELECT count(*)  from order_products WHERE order_id=" + orderId, new CountStatment());
        int deletedInOrdersWithProducts = jdbcTemplate.execute("DELETE from order_products WHERE order_id=" + orderId, new DeleteStatement());
        return countInOrdersWithProducts == deletedInOrdersWithProducts;
    }

    public boolean remove(int id) {
        int countDeletedOrders = jdbcTemplate.execute("DELETE from orders WHERE id=" + id, new DeleteStatement());
        return countDeletedOrders == 1;
    }

    public Order update(Order order) {
        jdbcTemplate.update("UPDATE orders set date=\'" + order.getDate() + "\' ,cost=" + order.getCost() + " where id=" + order.getId());
        return getOrder(order.getId());
    }
}
