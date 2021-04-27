package com.warehouse.server.controller.get;

import com.warehouse.server.model.ProductData;
import com.warehouse.server.repos.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


//Возвращает JSON всех товаров определенного заказа.
@Controller
public class GetProducts {
    @Autowired
    private ProductsRepo products;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

    //http://localhost:8081/getProducts?order=5
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/getProducts", method= RequestMethod.GET)
    public @ResponseBody
    List<ProductData> getProductsJson(@RequestParam(required = false) long order) {
        List<ProductData> arr = products.findAllByOrderByProductNumberDesc();
        arr.removeIf(s -> !(s.orderNumber == order));
        logger.info("Get products");
        return arr;
    }
}