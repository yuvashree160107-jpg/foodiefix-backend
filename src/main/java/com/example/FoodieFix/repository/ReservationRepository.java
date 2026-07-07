package com.example.FoodieFix.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodieFix.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAll(Pageable pageable);
}