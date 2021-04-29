package com.warehouse.server.service;

import com.warehouse.server.model.OrderEntity;

import java.util.List;

public interface OrderService {

    void saveOrder(String date, String address);

    List<OrderEntity> getAllOrders();

    void deleteOrder(long number);

}
