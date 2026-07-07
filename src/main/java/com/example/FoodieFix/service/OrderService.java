package com.example.FoodieFix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.FoodieFix.dto.OrderItemRequestDto;
import com.example.FoodieFix.dto.OrderRequestDto;
import com.example.FoodieFix.entity.DiningOrder;
import com.example.FoodieFix.entity.MenuItem;
import com.example.FoodieFix.entity.OrderItem;
import com.example.FoodieFix.entity.DiningOrder.OrderStatus;
import com.example.FoodieFix.entity.RestaurantTable;
import com.example.FoodieFix.entity.RestaurantTable.TableStatus;
import com.example.FoodieFix.exception.BusinessValidationException;
import com.example.FoodieFix.exception.ResourceNotFoundException;
import com.example.FoodieFix.repository.DiningOrderRepository;
import com.example.FoodieFix.repository.MenuItemRepository;
import com.example.FoodieFix.repository.OrderItemRepository;
import com.example.FoodieFix.repository.RestaurantTableRepository;

@Service
public class OrderService {

    private final DiningOrderRepository repo;
    private final RestaurantTableRepository tableRepo;
    private final MenuItemRepository menuItemRepo;
    private final OrderItemRepository orderItemRepo;

    public OrderService(DiningOrderRepository repo, RestaurantTableRepository tableRepo,
                         MenuItemRepository menuItemRepo, OrderItemRepository orderItemRepo) {
        this.repo = repo;
        this.tableRepo = tableRepo;
        this.menuItemRepo = menuItemRepo;
        this.orderItemRepo = orderItemRepo;
    }

    public Page<DiningOrder> getKitchenQueue(Pageable pageable) {
        List<OrderStatus> statuses = List.of(OrderStatus.PLACED, OrderStatus.PREPARING, OrderStatus.READY);
        return repo.findByStatusIn(statuses, pageable);
    }

    @Transactional
    public DiningOrder placeOrder(OrderRequestDto request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new BusinessValidationException("Order must contain at least one item");
        }

        if (!tableRepo.existsById(request.getTableId())) {
            throw new ResourceNotFoundException("Table not found with id: " + request.getTableId());
        }

        DiningOrder order = new DiningOrder();
        order.setTable_id(request.getTableId());
        order.setStatus(OrderStatus.PLACED);

        double total = 0.0;
        DiningOrder savedOrder = repo.save(order);

        List<OrderItem> orderItems = request.getItems().stream()
                .map(itemRequest -> buildOrderItem(savedOrder.getId(), itemRequest))
                .collect(Collectors.toList());

        for (OrderItem item : orderItems) {
            total += item.getPrice() * item.getQuantity();
            orderItemRepo.save(item);
        }

        savedOrder.setTotalAmount(total);
        return repo.save(savedOrder);
    }

    private OrderItem buildOrderItem(Long orderId, OrderItemRequestDto itemRequest) {
        if (itemRequest.getQuantity() == null || itemRequest.getQuantity() <= 0) {
            throw new BusinessValidationException("Item quantity must be greater than zero");
        }

        MenuItem menuItem = menuItemRepo.findById(itemRequest.getMenuItemId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Menu item not found with id: " + itemRequest.getMenuItemId()));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(orderId);
        orderItem.setMenu_item_id(menuItem.getId());
        orderItem.setQuantity(itemRequest.getQuantity());
        orderItem.setPrice(menuItem.getBasePrice());
        return orderItem;
    }

    @Transactional
    public DiningOrder updateStatus(Long id, OrderStatus status) {
        DiningOrder order = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        order.setStatus(status);

        if (status == OrderStatus.PAID && order.getTable_id() != null) {
            RestaurantTable table = tableRepo.findById(order.getTable_id())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Table not found with id: " + order.getTable_id()));
            table.setStatus(TableStatus.AVAILABLE);
            tableRepo.save(table);
        }

        return repo.save(order);
    }
}