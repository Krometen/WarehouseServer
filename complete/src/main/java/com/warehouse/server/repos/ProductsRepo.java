package com.warehouse.server.repos;

import com.warehouse.server.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий товаров
public interface ProductsRepo extends CrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByOrderByProductNumberDesc();
}