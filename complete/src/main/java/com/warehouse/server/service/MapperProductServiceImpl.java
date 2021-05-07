package com.warehouse.server.service;

import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public class MapperProductServiceImpl implements MapperProductService{

    @Override
    public ProductDto mapToProductDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setProductNumber(productEntity.getProductNumber());
        productDto.setProductName(productEntity.getProductName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setWeight(productEntity.getWeight());
        productDto.setDeleted(productEntity.isDeleted());
        productDto.setOrderDtoList(productEntity.getOrderEntityList());
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
        productEntity.setOrderEntityList(productDto.getOrderDtoList());
        return productEntity;
    }

}
