package com.warehouse.server.service.implementation;

import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.service.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperProductServiceImpl implements Mapper<ProductDto, ProductEntity> {

    private final Mapper<OrderDto, OrderEntity> mapperOrderService;

    @Autowired
    public MapperProductServiceImpl(@Qualifier("mapperOrderServiceImpl") Mapper<OrderDto, OrderEntity> mapperOrderService) {
        this.mapperOrderService = mapperOrderService;
    }

    @Override
    public ProductDto mapToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setProductNumber(productEntity.getProductNumber());
        productDto.setProductName(productEntity.getProductName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setWeight(productEntity.getWeight());
        //маппинг OrderEntityList в OrderDtoList
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity orderEntity:productEntity.getOrderEntityList()) {
            orderDtoList.add(mapperOrderService.mapToDto(orderEntity));
        }
        productDto.setOrderDtoList(orderDtoList);
        return productDto;
    }

    @Override
    public ProductEntity mapToEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDto.getId());
        productEntity.setProductNumber(productDto.getProductNumber());
        productEntity.setProductName(productDto.getProductName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setWeight(productDto.getWeight());
        //маппинг OrderDtoList в OrderEntityList
        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (OrderDto orderDto:productDto.getOrderDtoList()) {
            orderEntityList.add(mapperOrderService.mapToEntity(orderDto));
        }
        productEntity.setOrderEntityList(orderEntityList);
        return productEntity;
    }
}
