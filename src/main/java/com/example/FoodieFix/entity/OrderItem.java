package com.example.FoodieFix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long order_id;

    @NotNull
    private Long menu_item_id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Long id, @NotNull Long order_id, @NotNull Long menu_item_id, @NotNull Integer quantity,
            @NotNull Double price) {
        this.id = id;
        this.order_id = order_id;
        this.menu_item_id = menu_item_id;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getMenu_item_id() {
        return menu_item_id;
    }

    public void setMenu_item_id(Long menu_item_id) {
        this.menu_item_id = menu_item_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}