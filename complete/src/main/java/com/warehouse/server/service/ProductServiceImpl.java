package com.warehouse.server.service;

import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository products;

    private final static Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    public ProductServiceImpl(ProductRepository products) {
        this.products = products;
    }

    public void saveProduct(ProductEntity product){
        products.save(product);
        logger.info("Created Product ("+product.getProductName()+") for order number: "+product.getOrderNumber());
    }

    public List<ProductEntity> getAllProducts(){
        return products.findAll();
    }

}