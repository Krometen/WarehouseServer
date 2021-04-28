package com.warehouse.server.servicelayer;

import com.warehouse.server.controller.ProductController;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductService {

    @Autowired
    private ProductRepository products;

    private final static Logger logger = Logger.getLogger(ProductService.class.getName());

    public void saveProduct(ProductEntity product){
        products.save(product);
        logger.info("Created Product ("+product.getProductName()+") for order number: "+product.getOrderNumber());
    }

    public List<ProductEntity> getAllProducts(){
        return products.findAll();
    }

}
