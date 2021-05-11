package com.warehouse.server.service;

import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperProductServiceImpl implements MapperProductService{

    private final MapperOrderService mapperOrderService;

    @Autowired
    public MapperProductServiceImpl(MapperOrderService mapperOrderService) {
        this.mapperOrderService = mapperOrderService;
    }

    @Override
    public ProductDto mapToProductDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setProductNumber(productEntity.getProductNumber());
        productDto.setProductName(productEntity.getProductName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setWeight(productEntity.getWeight());
        productDto.setDeleted(productEntity.isDeleted());
        //маппинг OrderEntityList в OrderDtoList
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity orderEntity:productEntity.getOrderEntityList()) {
            orderDtoList.add(mapperOrderService.mapToOrderDto(orderEntity));
        }
        productDto.setOrderDtoList(orderDtoList);
        return productDto;
    }

    @Override
    public ProductEntity mapToProductEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDto.getId());
        productEntity.setProductNumber(productDto.getProductNumber());
        productEntity.setProductName(productDto.getProductName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setWeight(productDto.getWeight());
        productEntity.setDeleted(productDto.isDeleted());
        //маппинг OrderDtoList в OrderEntityList
        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (OrderDto orderDto:productDto.getOrderDtoList()) {
            orderEntityList.add(mapperOrderService.mapToOrderEntity(orderDto));
        }
        productEntity.setOrderEntityList(orderEntityList);
        return productEntity;
    }

}
