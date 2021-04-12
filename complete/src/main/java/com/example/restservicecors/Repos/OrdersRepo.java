package com.example.restservicecors.Repos;

import com.example.restservicecors.Model.OrderData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий заказов
public interface OrdersRepo extends CrudRepository<OrderData, Integer> {
    List<OrderData> findAllByOrderByOrderNumberDesc();
}