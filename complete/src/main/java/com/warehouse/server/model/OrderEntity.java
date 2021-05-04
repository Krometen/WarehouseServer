package com.warehouse.server.model;

import javax.persistence.*;
import java.time.LocalDate;


//JPA Заказов
@Entity
public class OrderEntity {
    @Id
    private long id;

    private String orderNumber;

    private LocalDate date;

    private String address;

    private boolean isDeleted;

    public OrderEntity(long id, String orderNumber, LocalDate date, String address, boolean isDeleted){
        this.id = id;
        this.orderNumber = orderNumber;
        this.date = date;
        this.address = address;
        this.orderNumber = orderNumber;
        this.isDeleted = isDeleted;
    }

    public OrderEntity(){
    }

    public long getId() {
        return id;
    }

    public String getOrderNumber() {
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
