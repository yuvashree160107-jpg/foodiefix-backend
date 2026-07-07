package com.example.FoodieFix.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.FoodieFix.dto.ReservationRequestDto;
import com.example.FoodieFix.entity.Reservation;
import com.example.FoodieFix.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Reservation> getReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getReservations(pageable);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER','RECEPTIONIST')")
    public ResponseEntity<Reservation> create(@Valid@RequestBody ReservationRequestDto request) {
        return ResponseEntity.ok(service.createReservation(request));
    }

    @PatchMapping("/{id}/seat")
    @PreAuthorize("hasAnyRole('MANAGER','RECEPTIONIST')")
    public ResponseEntity<Reservation> seat(@PathVariable Long id) {
        return ResponseEntity.ok(service.seatGuest(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER','RECEPTIONIST')")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        service.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}