package com.example.FoodieFix.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.FoodieFix.entity.RestaurantTable;
import com.example.FoodieFix.entity.RestaurantTable.TableStatus;
import com.example.FoodieFix.exception.BusinessValidationException;
import com.example.FoodieFix.exception.ResourceNotFoundException;
import com.example.FoodieFix.repository.RestaurantTableRepository;

@Service
public class TableService {

    private final RestaurantTableRepository repo;

    public TableService(RestaurantTableRepository repo) {
        this.repo = repo;
    }

    public Page<RestaurantTable> getAllTables(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public RestaurantTable createTable(RestaurantTable table) {
        if (table.getSeatingCapacity() == null || table.getSeatingCapacity() <= 0) {
            throw new BusinessValidationException("Seating capacity must be greater than zero");
        }
        if (table.getStatus() == null) {
            table.setStatus(TableStatus.AVAILABLE);
        }
        if (table.getIsActive() == null) {
            table.setIsActive(true);
        }
        return repo.save(table);
    }

    public RestaurantTable updateTableStatus(Long id, TableStatus status) {
        RestaurantTable table = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));
        table.setStatus(status);
        return repo.save(table);
    }

    public void deleteTable(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Table not found with id: " + id);
        }
        repo.deleteById(id);
    }
}