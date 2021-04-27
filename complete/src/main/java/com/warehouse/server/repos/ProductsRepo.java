package com.warehouse.server.repos;

import com.warehouse.server.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий товаров
public interface ProductsRepo extends CrudRepository<Product, Integer> {
    List<Product> findAllByOrderByProductNumberDesc();
}