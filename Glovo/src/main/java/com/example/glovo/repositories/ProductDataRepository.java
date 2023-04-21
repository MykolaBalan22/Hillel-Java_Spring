package com.example.glovo.repositories;

import com.example.glovo.entities.ProductEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDataRepository extends CrudRepository<ProductEntity, Integer> {

}
