package com.warehouse.server.repos;

import com.warehouse.server.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//Hibernate репозиторий заказов
@Repository
public interface OrderRepo extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAll();
}