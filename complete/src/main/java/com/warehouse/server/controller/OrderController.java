package com.warehouse.server.controller;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.service.OrderService;
import com.warehouse.server.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


//Создает заказ по дате и адресу
@RestController
public class OrderController {

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //http://localhost:8081/postNewOrder?date=01.02.21&address=PUSHKIN+STREET
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/postNewOrder")
    public void newOrder(@RequestParam(required = false) String date,
                         @RequestParam(required = false) String address) {
        logger.info("Creating a Order. Date: "+date+"; Address: "+address);
        orderService.saveOrder(date, address);
    }

    //http://localhost:8081/deleteOrder?number=1
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/deleteOrder")
    public void deleteOrder(@RequestParam(required = false, defaultValue = "undefined") long number) {
        logger.info("Delete Order №: "+number);
        orderService.deleteOrder(number);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/getOrders")
    public @ResponseBody
    List<OrderEntity> getOrdersJson() {
        logger.info("Get Orders");
        return orderService.getAllOrders();
    }

}