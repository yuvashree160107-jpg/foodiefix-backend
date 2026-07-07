package com.example.FoodieFix.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.FoodieFix.entity.RestaurantTable;
import com.example.FoodieFix.entity.RestaurantTable.TableStatus;
import com.example.FoodieFix.service.TableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    private final TableService service;

    public TableController(TableService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantTable>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllTables(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<RestaurantTable> create(@Valid@RequestBody RestaurantTable table) {
        return ResponseEntity.ok(service.createTable(table));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('MANAGER','RECEPTIONIST')")
    public ResponseEntity<RestaurantTable> updateStatus(@PathVariable Long id, @RequestParam TableStatus status) {
        return ResponseEntity.ok(service.updateTableStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}