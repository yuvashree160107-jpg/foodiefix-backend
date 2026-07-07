package com.example.FoodieFix.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodieFix.entity.MenuItem.MenuCategory;
import com.example.FoodieFix.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Page<MenuItem> findByCategory(MenuCategory category, Pageable pageable);
}