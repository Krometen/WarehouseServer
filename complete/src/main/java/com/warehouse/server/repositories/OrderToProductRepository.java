package com.warehouse.server.repositories;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.OrderToProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderToProductRepository extends CrudRepository<OrderToProductEntity, Long> {
    List<OrderToProductEntity> findAll();
}
