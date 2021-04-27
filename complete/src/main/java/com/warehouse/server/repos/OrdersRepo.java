package com.warehouse.server.repos;

import com.warehouse.server.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий заказов
public interface OrdersRepo extends CrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByOrderByOrderNumberDesc();
}