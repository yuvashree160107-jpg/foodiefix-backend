package com.example.FoodieFix.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String customerName;

    @NotNull
    private String CustomerPhoneNumber;

    @NotNull
    private Long Table_id;

    @NotNull
    private LocalDateTime reservationTime;

    @NotNull
    private Integer guestCount;

    @Enumerated(EnumType.STRING)
    ReservationStatus status;

    public enum  ReservationStatus {
    BOOKED,SEATED,CANCELLED
    }

    public Reservation() {
    }

    public Reservation(Long id, @NotBlank String customerName, @NotNull String customerPhoneNumber,
            @NotNull LocalDateTime reservationTime, @NotNull Integer guestCount, ReservationStatus status) {
        this.id = id;
        this.customerName = customerName;
        CustomerPhoneNumber = customerPhoneNumber;
        this.reservationTime = reservationTime;
        this.guestCount = guestCount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return CustomerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        CustomerPhoneNumber = customerPhoneNumber;
    }

    public Long getTable_id() {
        return Table_id;
    }

    public void setTable_id(Long table_id) {
        Table_id = table_id;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Integer getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(Integer guestCount) {
        this.guestCount = guestCount;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }


    
}