package com.example.FoodieFix.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.FoodieFix.entity.MenuItem;
import com.example.FoodieFix.service.MenuService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    public List<MenuItem> getMenu() {
        return service.getAllMenuItem(); 
    }

    @PostMapping
    public MenuItem save(@Valid@RequestBody MenuItem item) {
        return service.saveItem(item); 
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteItem(id);
        return "Menu item deleted successfully with ID: " + id; 
    }
}