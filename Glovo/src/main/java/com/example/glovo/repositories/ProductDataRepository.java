package com.example.glovo.repositories;

import com.example.glovo.entities.ProductEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDataRepository extends CrudRepository<ProductEntity,Integer> {
    @Query("delete from  order_products where prod_id=:Id")
    @Modifying
    void deleteCertainProduct(@Param("Id")int id);

    @Query("select count(*) from order_products where prod_id=:Id")
    int countProductsByIdInOrderWithProducts(@Param("Id")int id);

}
