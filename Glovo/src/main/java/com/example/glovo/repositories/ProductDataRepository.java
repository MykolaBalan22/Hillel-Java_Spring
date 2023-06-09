package com.example.glovo.repositories;

import com.example.glovo.entities.ProductEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDataRepository extends CrudRepository<ProductEntity, Integer> {
    @Query("select products.id ,products.name ,products.cost from products inner join order_products on id=prod_id where order_id=:Id")
    List<ProductEntity> getProductEntityByCertainOrder(@Param("Id") int id);
}
