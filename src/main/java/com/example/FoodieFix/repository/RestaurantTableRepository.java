package com.example.FoodieFix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodieFix.entity.RestaurantTable;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}