package com.example.FoodieFix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.FoodieFix.dto.OrderRequestDto;
import com.example.FoodieFix.entity.DiningOrder;
import com.example.FoodieFix.entity.DiningOrder.OrderStatus;
import com.example.FoodieFix.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER','SERVER')")
    public ResponseEntity<DiningOrder> placeOrder(@Valid@RequestBody OrderRequestDto request) {
        return ResponseEntity.ok(service.placeOrder(request));

    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('MANAGER','HEAD_CHEF','SERVER')")
    public ResponseEntity<DiningOrder> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}