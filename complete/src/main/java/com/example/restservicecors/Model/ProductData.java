package com.example.restservicecors.Model;

import javax.persistence.*;


//JPA Товаров
@Entity
public class ProductData {
    @Id
    public final long productNumber;
    
    public final String productName;

    public final long price;

    public final long weight;

    public final long orderNumber;

    public boolean deleted;

    public ProductData(long productNumber, String productName, long price, long weight, long orderNumber, boolean deleted) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.orderNumber = orderNumber;
        this.deleted = deleted;
    }

    public ProductData(){
        this.productNumber = -1;
        this.productName = "undefined";
        this.price = -1;
        this.weight = -1;
        this.orderNumber = -1;
        this.deleted = true;
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

    public long getOrder() {
        return orderNumber;
    }
    
    public boolean isDeleted() {
        return deleted;
    }
}