package com.warehouse.server.model;

import javax.persistence.*;
import java.util.List;


//JPA Товаров
@Entity
public class ProductEntity {

    @Id
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String productNumber;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToMany(mappedBy = "productEntityList")
    private List<OrderEntity> orderEntityList;

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

    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
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