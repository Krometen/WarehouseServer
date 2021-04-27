package com.warehouse.server.model;

import javax.persistence.*;


//JPA Товаров
@Entity
public class ProductEntity {
    @Id
    private long productNumber;

    private String productName;

    private double price;

    private double weight;

    private long orderNumber;

    private boolean isDeleted;

    public ProductEntity(long productNumber, String productName, double price, double weight, long orderNumber, boolean isDeleted) {
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

    public double getPrice() {
        return price;
    }

    public double getWeight() {
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