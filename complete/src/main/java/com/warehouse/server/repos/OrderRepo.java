package com.warehouse.server.repos;

import com.warehouse.server.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий заказов
public interface OrderRepo extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAll();
}