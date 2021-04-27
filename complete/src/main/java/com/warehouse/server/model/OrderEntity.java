package com.warehouse.server.model;

import javax.persistence.*;


//JPA Заказов
@Entity
public class OrderEntity {
    @Id
    private final long orderNumber;

    private final String date;

    private final String address;

    private final boolean isDeleted;

    public OrderEntity(String date, String address, long orderNumber, boolean isDeleted){
        this.date = date;
        this.address = address;
        this.orderNumber = orderNumber;
        this.isDeleted = isDeleted;
    }

    public OrderEntity(){
        this.isDeleted = true;
        this.orderNumber = -1;
        this.date = "undefined";
        this.address = "undefined";
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

//    public void delete(){
//        this.isDeleted = true;
//    }
}
