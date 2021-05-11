package com.warehouse.server.model;

import javax.persistence.*;
import java.util.List;


//JPA Товаров
@Entity
public class ProductEntity {

    @Id
    @SequenceGenerator(name="product_seq", sequenceName="seq_product", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(nullable = false)
    private Long id;

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

    @ManyToMany(mappedBy = "productEntityList", cascade = CascadeType.REMOVE)
    private List<OrderEntity> orderEntityList;

    public ProductEntity(Long id, String productNumber, String productName, double price, double weight, boolean isDeleted) {
        this.id = id;
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.isDeleted = isDeleted;
    }

    public ProductEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }
}