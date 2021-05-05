package com.warehouse.server.service;

import com.warehouse.server.model.OrderToProductEntity;

import java.util.List;

public interface OrderToProductService {

    void saveRelationship(long orderId, long productId);

    List<OrderToProductEntity> getAllRelationships();

    void deleteRelationship(long id);

}
