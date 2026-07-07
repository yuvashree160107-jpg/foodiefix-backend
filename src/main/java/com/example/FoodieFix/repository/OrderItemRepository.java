package com.example.FoodieFix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodieFix.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
