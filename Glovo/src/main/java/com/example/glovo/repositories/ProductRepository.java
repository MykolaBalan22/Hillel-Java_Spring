package com.example.glovo.repositories;

import com.example.glovo.models.Product;
import com.example.glovo.repositories.mappers.ProductMapper;
import com.example.glovo.repositories.statements.CountStatment;
import com.example.glovo.repositories.statements.DeleteStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getProductsByCertainOrder(int orderId) {
        String getQuery = """
                SELECT products.id ,products.name ,products.cost  FROM products 
                inner join order_products on products.id=order_products.prod_id
                 where order_products.order_id=
                """;
        try {
            return jdbcTemplate.query(getQuery + orderId, new ProductMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found", ex);
        }
    }

    public List<Product> addProductsForOrder(int id, List<Integer> list) {
        String queryForInsert = "INSERT INTO order_products (order_id,prod_id,created_date) value";
        for (Integer integer : list) {
            jdbcTemplate.execute(queryForInsert + "(" + id + "," + integer + ",\'" + LocalDate.now() + "\')");
        }
        return getProductsByCertainOrder(id);
    }

    public Product get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM products where id=" + id, new ProductMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found", ex);
        }
    }

    public Product add(Product product) {
        jdbcTemplate.execute("INSERT INTO products(id ,name,cost ) value(" + product.getId() + ",\'" + product.getName() + "\'," + product.getCost() + ")");
        return get(product.getId());
    }

    public boolean remove(int id) {
        int countDeletedProducts = jdbcTemplate.execute("DELETE from products WHERE id=" + id, new DeleteStatement());
        return countDeletedProducts == 1;
    }

    public boolean removeCertainProductForAllOrders(int productId) {
        int countInOrders = jdbcTemplate.execute("SELECT count(*)  from order_products WHERE prod_id=" + productId, new CountStatment());
        int deletedInOrders = jdbcTemplate.execute("DELETE from order_products WHERE prod_id=" + productId, new DeleteStatement());
        return countInOrders == deletedInOrders;
    }

    public Product update(Product product) {
        jdbcTemplate.update("UPDATE products set name=\'" + product.getName() + "\' ,cost=" + product.getCost() + " where id=" + product.getId());
        return get(product.getId());
    }

    public List<Product> updateProductsByCertainOrder(int orderId, List<Integer> list) {
        removeAllProductsForOrder(orderId);
        return addProductsForOrder(orderId, list);
    }

    private void removeAllProductsForOrder(int orderId) {
        try {
            jdbcTemplate.execute("DELETE from order_products WHERE order_id=" + orderId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found", ex);
        }
    }
}
