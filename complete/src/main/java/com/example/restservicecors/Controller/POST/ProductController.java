package com.example.restservicecors.Controller.POST;

import com.example.restservicecors.Controller.GET.GetOrders;
import com.example.restservicecors.Model.ProductData;
import com.example.restservicecors.Repos.ProductsRepo;
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
    private ProductsRepo products;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

    //http://localhost:8081/postNewProduct?productName=phone&price=20000&weight=200&orderNumber=1
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/postNewProduct")
    public ProductData newProduct(@RequestParam(required = false, defaultValue = "undefined") String productName,
                                  @RequestParam(required = false) long price,
                                  @RequestParam(required = false) long weight,
                                  @RequestParam(required = false) long orderNumber){
        List<ProductData> allProducts = products.findAllByOrderByProductNumberDesc();
        long counter = allProducts.size()+1;
        ProductData product = new ProductData(counter, productName, price, weight, orderNumber, false);
        try {
            products.save(product);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe + "\n " + product);
        }
        logger.info("Created Product ("+productName+") for order number: "+orderNumber);
        return product;
    }
}