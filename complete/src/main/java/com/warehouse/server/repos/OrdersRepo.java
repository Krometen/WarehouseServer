package com.warehouse.server.repos;

import com.warehouse.server.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий заказов
public interface OrdersRepo extends CrudRepository<Order, Integer> {
    List<Order> findAllByOrderByOrderNumberDesc();
}