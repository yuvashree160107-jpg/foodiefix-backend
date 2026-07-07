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
@Table(name = "restaurant_tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Integer tableNumber;

    @NotNull
    private Integer seatingCapacity ;

    @Enumerated(EnumType.STRING)
    TableStatus status;

    public enum TableStatus{
    AVAILABLE,OCCUPIED,RESERVED,OUT_OF_SERVICE
    }
    @NotNull
    private Boolean isActive;
    
    public RestaurantTable() {
    }
    public RestaurantTable(long id, @NotNull Integer tableNumber, @NotNull Integer seatingCapacity, TableStatus status,
            @NotNull Boolean isActive) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.seatingCapacity = seatingCapacity;
        this.status = status;
        this.isActive = isActive;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Integer getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }
    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }
    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
    public TableStatus getStatus() {
        return status;
    }
    public void setStatus(TableStatus status) {
        this.status = status;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
    