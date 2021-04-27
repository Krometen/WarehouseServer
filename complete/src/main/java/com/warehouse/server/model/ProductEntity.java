package com.warehouse.server.model;

import javax.persistence.*;


//JPA Товаров
@Entity
public class ProductEntity {
    @Id
    private long productNumber;

    private String productName;

    private long price;

    private long weight;

    private long orderNumber;

    private boolean isDeleted;

    public ProductEntity(long productNumber, String productName, long price, long weight, long orderNumber, boolean isDeleted) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.orderNumber = orderNumber;
        this.isDeleted = isDeleted;
    }

    public ProductEntity(){
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