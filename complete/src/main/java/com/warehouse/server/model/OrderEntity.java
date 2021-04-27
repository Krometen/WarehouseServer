package com.warehouse.server.model;

import javax.persistence.*;
import java.time.LocalDate;


//JPA Заказов
@Entity
public class OrderEntity {
    @Id
    private long orderNumber;

    private LocalDate date;

    private String address;

    private boolean isDeleted;

    public OrderEntity(LocalDate date, String address, long orderNumber, boolean isDeleted){
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

    public LocalDate getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
