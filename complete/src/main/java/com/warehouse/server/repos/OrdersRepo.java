package com.warehouse.server.repos;

import com.warehouse.server.model.OrderData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий заказов
public interface OrdersRepo extends CrudRepository<OrderData, Integer> {
    List<OrderData> findAllByOrderByOrderNumberDesc();
}