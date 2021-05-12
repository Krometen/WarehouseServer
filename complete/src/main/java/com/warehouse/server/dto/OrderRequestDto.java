package com.warehouse.server.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderRequestDto {

    private String orderNumber;
    private LocalDate date;
    private String address;
    private List<Long> productIdList;

    public List<Long> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Long> productList) {
        this.productIdList = productList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
