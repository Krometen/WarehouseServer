package com.warehouse.server.controller.post;

import com.warehouse.server.controller.get.GetOrders;
import com.warehouse.server.model.Order;
import com.warehouse.server.repos.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;


//Создает заказ по дате и адресу
@RestController
public class OrderController {
    @Autowired
    private OrdersRepo orders;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

    //http://localhost:8081/postNewOrder?date=01.02.21&address=PUSHKIN+STREET
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/postNewOrder")
    public Order newOrder(@RequestParam(required = false, defaultValue = "undefined") String date,
                          @RequestParam(required = false, defaultValue = "undefined") String address) {
        List<Order> allOrders = orders.findAllByOrderByOrderNumberDesc();
        long counter = allOrders.size()+1;
        Order order = new Order(date, address, counter, false);
        try {
            orders.save(order);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe + "\n " + order);
        }
        logger.info("Order has been created: №"+counter);

        return order;
    }
}