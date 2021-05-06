package com.warehouse.server.service;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.ProductEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    void saveOrder(String orderNumber, LocalDate date, String address, List<ProductEntity> productEntityList);

    List<OrderEntity> getAllOrders();

    void deleteOrder(long id);

}
