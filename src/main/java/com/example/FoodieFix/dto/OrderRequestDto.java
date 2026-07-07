package com.example.FoodieFix.dto;

import java.util.List;

public class OrderRequestDto {

    private Long tableId;
    private List<OrderItemRequestDto> items;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public List<OrderItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDto> items) {
        this.items = items;
    }
}