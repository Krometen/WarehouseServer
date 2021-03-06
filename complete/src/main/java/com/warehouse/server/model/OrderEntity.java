package com.warehouse.server.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


//JPA Заказов
@Entity
public class OrderEntity {

    @Id
    @SequenceGenerator(name="orders_seq", sequenceName="seq_orders", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String address;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "order_to_product_entity",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> productEntityList;

    public OrderEntity(Long id, String orderNumber, LocalDate date, String address, List<ProductEntity> productEntityList){
        this.id = id;
        this.orderNumber = orderNumber;
        this.date = date;
        this.address = address;
        this.productEntityList = productEntityList;
    }

    public OrderEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    public void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }

}
