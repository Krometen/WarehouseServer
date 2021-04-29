package com.warehouse.server.service;

import com.warehouse.server.model.ProductEntity;
import java.util.List;

public interface ProductService {

    void saveProduct(ProductEntity product);

    List<ProductEntity> getAllProducts();

}
