package com.warehouse.server.controller;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/postNewProduct")
    public ResponseEntity<ProductEntity> newProduct(@RequestBody ProductEntity productEntity){
        logger.info("Created Product ("+productEntity.getProductName()+") for Order "+productEntity.getOrderNumber());
        productService.saveProduct(productEntity.getProductName(), productEntity.getPrice(), productEntity.getWeight(),
                productEntity.getOrderNumber());
        return new ResponseEntity<>(HttpStatus.OK);
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