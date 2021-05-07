package com.warehouse.server.service;

import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.ProductEntity;

public interface MapperProductService {

    ProductDto mapToProductDto(ProductEntity productEntity);

    ProductEntity mapToProductEntity(ProductDto productDto);

}
