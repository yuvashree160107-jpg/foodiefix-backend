package com.example.FoodieFix.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.FoodieFix.entity.MenuItem;
import com.example.FoodieFix.exception.ResourceNotFoundException;
import com.example.FoodieFix.repository.MenuItemRepository;

@Service
public class MenuService {

    private final MenuItemRepository repo;

    public MenuService(MenuItemRepository repo) {
        this.repo = repo;
    }

    // SIMPLIFIED: Changed from Page<MenuItem> to a standard List<MenuItem>
    public List<MenuItem> getAllMenuItem() {
        return repo.findAll(); // Fetches everything from the database in a simple array list
    }

    public MenuItem saveItem(MenuItem item) {
        return repo.save(item);
    }

    public void deleteItem(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Menu item not found with id: " + id);
        }
        repo.deleteById(id);
    }
}