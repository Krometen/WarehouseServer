package com.warehouse.server.controller;

import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


//Создает товар по имени, номеру, цене, весу и номеру его заказа
@RestController
public class ProductController {

    private final ProductService productService;

    private final static Logger logger = Logger.getLogger(ProductController.class.getName());

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/post-new-product")
    public ResponseEntity<ProductDto> newProduct(@RequestBody ProductDto product){
        logger.info("Created Product. Name: "+product.getProductName()+"; Number: "+product.getProductNumber());
        productService.saveProduct(product.getProductNumber(), product.getProductName(), product.getPrice(), product.getWeight());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id){
        logger.info("Delete Product "+id);
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:8081/getProducts?orderId=5
    @GetMapping(value="/get-products")
    public @ResponseBody
    List<ProductDto> getProductsJson(@RequestParam(required = false) Long orderId) {
        logger.info("Get Products of the Order "+orderId);
        return productService.getProducts(orderId);
    }

}