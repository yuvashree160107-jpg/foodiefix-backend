package com.example.FoodieFix.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodieFix.entity.DiningOrder;
import com.example.FoodieFix.entity.DiningOrder.OrderStatus;

public interface DiningOrderRepository extends JpaRepository<DiningOrder, Long> {
    Page<DiningOrder> findByStatusIn(List<OrderStatus> statuses, Pageable pageable);
}