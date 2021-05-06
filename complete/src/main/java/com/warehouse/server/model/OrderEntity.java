package com.warehouse.server.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


//JPA Заказов
@Entity
public class OrderEntity {
    @Id
    private long id;

    private String orderNumber;

    private LocalDate date;

    private String address;

    private boolean isDeleted;

    @ManyToMany
    @JoinTable(
            name = "order_to_product_entity",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> productEntityList;

    public OrderEntity(long id, String orderNumber, LocalDate date, String address, boolean isDeleted, List<ProductEntity> productEntityList){
        this.id = id;
        this.orderNumber = orderNumber;
        this.date = date;
        this.address = address;
        this.isDeleted = isDeleted;
        this.productEntityList = productEntityList;
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

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

}
