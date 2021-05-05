package com.warehouse.server.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
//@IdClass(OrderToProductId.class)
public class OrderToProductEntity {

    @EmbeddedId
    private OrderToProductId id;
//
//    @Id
//    private long orderId;
//
//    @Id
//    private long productId;

//    public OrderToProductEntity(long orderId, long productId) {
//        this.orderId = orderId;
//        this.productId = productId;
//    }
//
//    public OrderToProductEntity() {}
//
//    public long getOrderId() {
//        return orderId;
//    }
//
//    public long getProductId() {
//        return productId;
//    }
//
//    public void setOrderId(long orderId) {
//        this.orderId = orderId;
//    }
//
//    public void setProductId(long productId) {
//        this.productId = productId;
//    }

    public OrderToProductEntity(OrderToProductId id) {
        this.id = id;
    }

    public OrderToProductEntity() {}

    public OrderToProductId getId() {
        return id;
    }

    public void setId(OrderToProductId id) {
        this.id = id;
    }

}
