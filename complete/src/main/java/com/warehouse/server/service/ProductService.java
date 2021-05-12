package com.warehouse.server.service;

import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.dto.ProductDto;
import java.util.List;

public interface ProductService {

    void saveProduct(ProductDto productDto);

    void deleteProduct(Long id);

    List<ProductDto> getProducts(Long orderId);

}
