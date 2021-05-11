package com.warehouse.server.service;

import com.warehouse.server.dto.ProductDto;
import java.util.List;

public interface ProductService {

    void saveProduct(String productNumber, String productName, double price, double weight);

    void deleteProduct(Long id);

    List<ProductDto> getProducts(Long orderId);

}
