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

    @PostMapping("/postNewOrder")
    public ResponseEntity<OrderEntity> newOrder(@RequestBody OrderEntity order) {
        logger.info("Creating a Order. Number: "+order.getOrderNumber()+"; Date: "+order.getDate()+"; " +
                "Address: "+order.getAddress());
        orderService.saveOrder(order.getOrderNumber(), order.getDate(), order.getAddress());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //http://localhost:8081/deleteOrder?number=1
    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Void> deleteOrder(@RequestParam long id) {
        logger.info("Delete Order by id"+id);
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/getOrders")
    public @ResponseBody
    List<OrderEntity> getOrdersJson() {
        logger.info("Get Orders");
        return orderService.getAllOrders();
    }

}