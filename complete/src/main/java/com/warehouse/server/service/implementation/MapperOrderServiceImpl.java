package com.warehouse.server.service.implementation;

import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.service.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MapperOrderServiceImpl implements Mapper<OrderDto, OrderEntity> {

    @Override
    public OrderDto mapToDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setOrderNumber(orderEntity.getOrderNumber());
        orderDto.setDate(orderEntity.getDate());
        orderDto.setAddress(orderEntity.getAddress());
        return orderDto;
    }

    @Override
    public OrderEntity mapToEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getId());
        orderEntity.setOrderNumber(orderDto.getOrderNumber());
        orderEntity.setDate(orderDto.getDate());
        orderEntity.setAddress(orderDto.getAddress());
        return orderEntity;
    }

}
