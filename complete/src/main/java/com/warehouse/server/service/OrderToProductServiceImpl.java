package com.warehouse.server.service;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.OrderToProductEntity;
import com.warehouse.server.model.OrderToProductId;
import com.warehouse.server.repositories.OrderToProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderToProductServiceImpl implements OrderToProductService{

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    private final OrderToProductRepository relationships;

    @Autowired
    public OrderToProductServiceImpl(OrderToProductRepository relationships) {
        this.relationships = relationships;
    }

    @Override
    public void saveRelationship(long orderId, long productId) {
        OrderToProductId id = new OrderToProductId(orderId, productId);
        OrderToProductEntity orderToProduct = new OrderToProductEntity(id);
        try {
            relationships.save(orderToProduct);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe);
        }
    }

    @Override
    public void deleteRelationship(OrderToProductEntity relationship) {
        try {
            relationships.delete(relationship);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe);
        }
    }

    @Override
    public List<OrderToProductEntity> getAllRelationships() {
        return relationships.findAll();
    }

}
