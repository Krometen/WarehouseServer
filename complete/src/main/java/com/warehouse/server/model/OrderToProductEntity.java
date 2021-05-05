package com.warehouse.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderToProductEntity {

    @Id
    private long id;

    private long orderId;

    private long productId;

    public OrderToProductEntity(long id, long orderId, long productId) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderToProductEntity(){}

    public long getId() {
        return id;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getProductId() {
        return productId;
    }

}
