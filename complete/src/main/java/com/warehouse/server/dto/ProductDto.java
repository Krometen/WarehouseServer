package com.warehouse.server.dto;

import com.warehouse.server.model.OrderEntity;

import java.util.List;

public class ProductDto {

    private long id;
    private String productNumber;
    private String productName;
    private double price;
    private double weight;
    private boolean isDeleted;
    private List<OrderEntity> orderDtoList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<OrderEntity> getOrderDtoList() {
        return orderDtoList;
    }

    public void setOrderDtoList(List<OrderEntity> orderDtoList) {
        this.orderDtoList = orderDtoList;
    }
}
