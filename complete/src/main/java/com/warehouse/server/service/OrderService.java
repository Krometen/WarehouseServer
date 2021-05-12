package com.warehouse.server.service;

import com.warehouse.server.dto.OrderRequestDto;
import com.warehouse.server.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void saveOrder(OrderRequestDto orderRequestDto);

    List<OrderDto> getAllOrders();

    void deleteOrder(Long id);

}
