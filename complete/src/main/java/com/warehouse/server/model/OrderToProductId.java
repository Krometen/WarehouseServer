package com.warehouse.server.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderToProductId implements Serializable {

    private long orderId;
    private long productId;

    public OrderToProductId(long orderId, long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderToProductId() {
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderToProductId that = (OrderToProductId) o;
        return orderId == that.orderId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

}
