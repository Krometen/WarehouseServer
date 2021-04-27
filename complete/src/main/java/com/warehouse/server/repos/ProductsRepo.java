package com.warehouse.server.repos;

import com.warehouse.server.model.ProductData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий товаров
public interface ProductsRepo extends CrudRepository<ProductData, Integer> {
    List<ProductData> findAllByOrderByProductNumberDesc();
}