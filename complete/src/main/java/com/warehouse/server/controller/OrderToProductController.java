package com.warehouse.server.controller;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.OrderToProductEntity;
import com.warehouse.server.service.OrderServiceImpl;
import com.warehouse.server.service.OrderToProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrderToProductController {

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    private final OrderToProductService orderToProductService;

    @Autowired
    public OrderToProductController(OrderToProductService orderToProductService) {
        this.orderToProductService = orderToProductService;
    }

    @PostMapping("/postNewRelationship")
    public ResponseEntity<OrderToProductEntity> newRelationship(@RequestBody OrderToProductEntity relationship) {

        logger.info("Creating a Relationship. Order_id: "+relationship.getId().getOrderId()+"; Product_id: "+relationship.getId().getProductId());
        orderToProductService.saveRelationship(relationship.getId().getOrderId(), relationship.getId().getProductId());
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRelationship")
    public ResponseEntity<Void> deleteRelationship(@RequestBody OrderToProductEntity relationship) {
        logger.info("Delete Relationship by Order_id: "+relationship.getId().getOrderId()+"; Product_id: "+
                relationship.getId().getProductId());
        orderToProductService.deleteRelationship(relationship);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/getAllRelationships")
    public @ResponseBody
    List<OrderToProductEntity> getAllRelationships() {
        logger.info("Get Relationships");
        return orderToProductService.getAllRelationships();
    }

}
