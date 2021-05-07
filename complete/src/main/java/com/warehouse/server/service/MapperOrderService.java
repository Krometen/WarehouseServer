package com.warehouse.server.service;

import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.model.OrderEntity;

public interface MapperOrderService {

    OrderDto mapToOrderDto(OrderEntity orderEntity);

    OrderEntity mapToOrderEntity(OrderDto orderDto);

}
