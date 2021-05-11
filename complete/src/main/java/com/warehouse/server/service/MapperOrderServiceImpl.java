package com.warehouse.server.service;

import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.model.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class MapperOrderServiceImpl implements MapperOrderService{

    @Override
    public OrderDto mapToOrderDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setOrderNumber(orderEntity.getOrderNumber());
        orderDto.setDate(orderEntity.getDate());
        orderDto.setAddress(orderEntity.getAddress());
        return orderDto;
    }

    @Override
    public OrderEntity mapToOrderEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getId());
        orderEntity.setOrderNumber(orderDto.getOrderNumber());
        orderEntity.setDate(orderDto.getDate());
        orderEntity.setAddress(orderDto.getAddress());
        orderEntity.setDeleted(orderDto.isDeleted());
        return orderEntity;
    }

}
