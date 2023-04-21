package com.example.glovo.repositories;

import com.example.glovo.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDataRepository extends CrudRepository<ProductEntity,Integer> {

}
