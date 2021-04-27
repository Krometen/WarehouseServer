package com.warehouse.server.repos;

import com.warehouse.server.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий товаров
public interface ProductRepo extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findAll();
}