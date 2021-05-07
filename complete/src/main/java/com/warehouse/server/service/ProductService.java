package com.warehouse.server.service;

import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.ProductEntity;
import java.util.List;

public interface ProductService {

    void saveProduct(String productNumber, String productName, double price, double weight);

    void deleteProduct(long id);

    List<ProductDto> getProducts(long orderId);

}
