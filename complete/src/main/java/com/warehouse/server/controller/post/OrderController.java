package com.warehouse.server.controller.post;

import com.warehouse.server.controller.get.GetOrders;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;


//Создает заказ по дате и адресу
@RestController
public class OrderController {
    @Autowired
    private OrderRepository orders;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

    //http://localhost:8081/postNewOrder?date=01.02.21&address=PUSHKIN+STREET
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/postNewOrder")
    public OrderEntity newOrder(@RequestParam(required = false) LocalDate date,
                                @RequestParam(required = false) String address) {
        List<OrderEntity> allOrders = orders.findAll();
        long counter = allOrders.size()+1;
        OrderEntity order = new OrderEntity(date, address, counter, false);
        try {
            orders.save(order);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe + "\n " + order);
        }
        logger.info("Order has been created: №"+counter);

        return order;
    }
}