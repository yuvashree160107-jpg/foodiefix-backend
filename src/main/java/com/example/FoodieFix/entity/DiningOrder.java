package com.example.FoodieFix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "dining_orders")
public class DiningOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long table_id;
    
    @NotNull
    private Long server_id;

    @Enumerated(EnumType.STRING)
    OrderStatus  status;

    public enum OrderStatus {
    PLACED,PREPARING,READY,PAID,CANCELLED
}

    @NotNull
    private Double totalAmount;

    
    public DiningOrder() {
    }

    public DiningOrder(Long id, Long table_id, @NotNull Long server_id, OrderStatus status,
            @NotNull Double totalAmount) {
        this.id = id;
        this.table_id = table_id;
        this.server_id = server_id;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTable_id() {
        return table_id;
    }

    public void setTable_id(Long table_id) {
        this.table_id = table_id;
    }

    public Long getServer_id() {
        return server_id;
    }

    public void setServer_id(Long server_id) {
        this.server_id = server_id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    
}