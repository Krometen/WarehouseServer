package com.warehouse.server.service.implementation;

import com.warehouse.server.dto.OrderRequestDto;
import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repositories.OrderRepository;
import com.warehouse.server.service.Mapper;
import com.warehouse.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    private final OrderRepository orders;

    private final Mapper<OrderDto, OrderEntity> mapperOrderService;

    @Autowired
    public OrderServiceImpl(OrderRepository orders,
                            @Qualifier("mapperOrderServiceImpl") Mapper<OrderDto, OrderEntity> mapperOrderService) {
        this.orders = orders;
        this.mapperOrderService = mapperOrderService;
    }

    @Override
    public void saveOrder(OrderRequestDto orderRequestDto){
        List<ProductEntity> productEntityList = new ArrayList<>(); //лист сущностей продуктов из листа айди продуктов для связей
        for (Long id:
        orderRequestDto.getProductIdList()) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(id);
            productEntityList.add(productEntity);
        }
        OrderEntity orderEntity = new OrderEntity(null, orderRequestDto.getOrderNumber(), orderRequestDto.getDate(), orderRequestDto.getAddress(), productEntityList);
        try {
            orders.save(orderEntity);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        try {
            orders.deleteById(id);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe);
        }
    }

    @Override
    public List<OrderDto> getAllOrders(){
        List<OrderEntity> orderEntityList = orders.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity orderEntity:orderEntityList) {
            orderDtoList.add(mapperOrderService.mapToDto(orderEntity));
        }
        return orderDtoList;
    }

}
