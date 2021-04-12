package com.example.restservicecors.Repos;

import com.example.restservicecors.Model.OrderData;
import com.example.restservicecors.Model.ProductData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Hibernate репозиторий товаров
public interface ProductsRepo extends CrudRepository<ProductData, Integer> {
    List<ProductData> findAllByOrderByProductNumberDesc();
}