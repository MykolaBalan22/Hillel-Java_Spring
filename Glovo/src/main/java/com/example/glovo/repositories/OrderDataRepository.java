package com.example.glovo.repositories;

import com.example.glovo.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDataRepository extends CrudRepository<OrderEntity,Integer> {
}
