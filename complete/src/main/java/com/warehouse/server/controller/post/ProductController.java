package com.warehouse.server.controller.post;

import com.warehouse.server.controller.get.GetOrders;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;


//Создает товар по имени, цене, весу и номеру его заказа
@RestController
public class ProductController {
    @Autowired
    private ProductRepo products;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

    //http://localhost:8081/postNewProduct?productName=phone&price=20000&weight=200&orderNumber=1
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/postNewProduct")
    public ProductEntity newProduct(@RequestParam(required = false) String productName,
                                    @RequestParam(required = false) double price,
                                    @RequestParam(required = false) double weight,
                                    @RequestParam(required = false) long orderNumber){
        List<ProductEntity> allProducts = products.findAll();
        long counter = allProducts.size()+1;
        ProductEntity product = new ProductEntity(counter, productName, price, weight, orderNumber, false);
        try {
            products.save(product);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe + "\n " + product);
        }
        logger.info("Created Product ("+productName+") for order number: "+orderNumber);
        return product;
    }
}