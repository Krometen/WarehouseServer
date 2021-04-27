package com.warehouse.server.model;

import javax.persistence.*;


//JPA Заказов
@Entity
public class Order {
    @Id
    public final long orderNumber;

    public final String date;

    public final String address;

    public boolean deleted;

    public Order(String date, String address, long orderNumber, boolean deleted){
        this.date = date;
        this.address = address;
        this.orderNumber = orderNumber;
        this.deleted = deleted;
    }

    public Order(){
        this.deleted = true;
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
        return deleted;
    }

    public void delete(){
        this.deleted = true;
    }
}
