package com.example.restservicecors.Model;

import javax.persistence.*;


//JPA Заказов
@Entity
public class OrderData {
    @Id
    public final long orderNumber;

    public final String date;

    public final String address;

    public boolean deleted;

    public OrderData(String date, String address, long orderNumber, boolean deleted){
        this.date = date;
        this.address = address;
        this.orderNumber = orderNumber;
        this.deleted = deleted;
    }

    public OrderData(){
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
