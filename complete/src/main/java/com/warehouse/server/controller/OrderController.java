package com.warehouse.server.controller;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.service.OrderService;
import com.warehouse.server.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/postNewOrder")
    public ResponseEntity<OrderEntity> newOrder(@RequestBody OrderEntity order) {
        logger.info("Creating a Order. Date: "+order.getDate()+"; Address: "+order.getAddress());
        orderService.saveOrder(order.getDate(), order.getAddress());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //http://localhost:8081/deleteOrder?number=1
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Void> deleteOrder(@RequestParam long number) {
        logger.info("Delete Order "+number);
        orderService.deleteOrder(number);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/getOrders")
    public @ResponseBody
    List<OrderEntity> getOrdersJson() {
        logger.info("Get Orders");
        return orderService.getAllOrders();
    }

}