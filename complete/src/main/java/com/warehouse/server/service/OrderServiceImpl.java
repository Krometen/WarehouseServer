package com.warehouse.server.service;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orders;

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    public OrderServiceImpl(OrderRepository orders) {
        this.orders = orders;
    }

    public void saveOrder(OrderEntity order){
        orders.save(order);
        logger.info("Creating a Order: №"+order.getOrderNumber());
    }

    public List<OrderEntity> getAllOrders(){
        return orders.findAll();
    }

}
