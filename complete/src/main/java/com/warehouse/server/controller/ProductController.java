package com.warehouse.server.controller;

import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


//Создает товар по имени, цене, весу и номеру его заказа
@RestController
public class ProductController {

    private final ProductService productService;

    private final static Logger logger = Logger.getLogger(ProductController.class.getName());

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //http://localhost:8081/postNewProduct?productName=phone&price=20000&weight=200&orderNumber=1
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/postNewProduct")
    public void newProduct(@RequestParam(required = false) String productName,
                           @RequestParam(required = false) double price,
                           @RequestParam(required = false) double weight,
                           @RequestParam(required = false) long orderNumber){
        logger.info("Created Product ("+productName+") for Order "+orderNumber);
        productService.saveProduct(productName, price, weight, orderNumber);
    }

    //http://localhost:8081/deleteProduct?number=11
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/deleteProduct")
    public void deleteProduct(@RequestParam(required = false) long number){
        logger.info("Delete Product "+number);
        productService.deleteProduct(number);
    }

    //http://localhost:8081/getProducts?orderNumber=5
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/getProducts")
    public @ResponseBody
    List<ProductEntity> getProductsJson(@RequestParam(required = false) long orderNumber) {
        logger.info("Get Products of the Order "+orderNumber);
        return productService.getProducts(orderNumber);
    }

}