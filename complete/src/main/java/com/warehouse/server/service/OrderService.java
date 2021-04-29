package com.warehouse.server.service;

import com.warehouse.server.model.OrderEntity;
import java.util.List;

public interface OrderService {

    void saveOrder(OrderEntity order);

    List<OrderEntity> getAllOrders();

}
