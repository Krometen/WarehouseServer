package com.warehouse.server.model;

import javax.persistence.*;


//JPA Товаров
@Entity
public class ProductEntity {
    @Id
    private final long productNumber;

    private final String productName;

    private final long price;

    private final long weight;

    private final long orderNumber;

    private final boolean isDeleted;

    public ProductEntity(long productNumber, String productName, long price, long weight, long orderNumber, boolean isDeleted) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.orderNumber = orderNumber;
        this.isDeleted = isDeleted;
    }

    public ProductEntity(){
        this.productNumber = -1;
        this.productName = "undefined";
        this.price = -1;
        this.weight = -1;
        this.orderNumber = -1;
        this.isDeleted = true;
    }

    public String getProductName() {
        return productName;
    }

    public long getPrice() {
        return price;
    }

    public long getWeight() {
        return weight;
    }

    public long getProductNumber() {
        return productNumber;
    }

    public long getOrderNumber(){
        return orderNumber;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}