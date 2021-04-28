package com.warehouse.server.repositories;

import com.warehouse.server.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//Hibernate репозиторий товаров
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findAll();
}