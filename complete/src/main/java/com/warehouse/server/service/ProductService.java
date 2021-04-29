package com.warehouse.server.service;

import com.warehouse.server.model.ProductEntity;
import java.util.List;

public interface ProductService {

    void saveProduct(String productName, double price, double weight, long orderNumber);

    void deleteProduct(long number);

    List<ProductEntity> getProducts(long orderNumber);

}
