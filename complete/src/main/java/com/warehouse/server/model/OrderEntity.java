package com.warehouse.server.model;

import javax.persistence.*;


//JPA Заказов
@Entity
public class OrderEntity {
    @Id
    private long orderNumber;

    private String date;

    private String address;

    private boolean isDeleted;

    public OrderEntity(String date, String address, long orderNumber, boolean isDeleted){
        this.date = date;
        this.address = address;
        this.orderNumber = orderNumber;
        this.isDeleted = isDeleted;
    }

    public OrderEntity(){
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
