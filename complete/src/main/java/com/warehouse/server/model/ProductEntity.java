package com.warehouse.server.model;

import javax.persistence.*;


//JPA Товаров
@Entity
public class ProductEntity {
    @Id
    private long id;

    private String productNumber;

    private String productName;

    private double price;

    private double weight;

    private boolean isDeleted;

    public ProductEntity(long id, String productNumber, String productName, double price, double weight, boolean isDeleted) {
        this.id = id;
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.isDeleted = isDeleted;
    }

    public ProductEntity(){
    }

    public long getId() {
        return id;
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

    public String getProductNumber() {
        return productNumber;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}