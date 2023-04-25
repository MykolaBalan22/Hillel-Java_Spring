package com.example.glovo.repositories;

import com.example.glovo.entities.OrderWithProductEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderWithProductsRepository extends CrudRepository<OrderWithProductEntity, Integer> {
    @Query("delete from  order_products where prod_id=:Id")
    @Modifying
    void deleteCertainProduct(@Param("Id")int id);
    @Query("delete from  order_products where order_id=:Id")
    @Modifying
    void deleteProductsForOrder(@Param("Id")int id);

    @Query("select count(*) from order_products where prod_id=:Id")
    int countProductsByIdInOrderWithProducts(@Param("Id")int id);

    @Query("select count(*) from order_products where order_id=:Id")
    int countOrderProductsByOrderId(@Param("Id")int id);
    @Query("INSERT INTO order_products (order_id,prod_id,created_date) value (:order_id,:prod_id,:created_date)" )
    @Modifying
    void addProductByOrder(@Param("order_id")int orderId, @Param("prod_id")int prodId, @Param("created_date") LocalDate createdDate);
}
