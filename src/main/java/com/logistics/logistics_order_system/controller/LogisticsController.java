package com.logistics.logistics_order_system.controller;

import com.logistics.logistics_order_system.model.LogisticsOrder;
import com.logistics.logistics_order_system.model.OrderStatus;
import com.logistics.logistics_order_system.service.LogisticsService;
import com.logistics.logistics_order_system.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class LogisticsController {

    private final LogisticsService service;
    private final OrderRepository repo;

    @PostMapping
    public ResponseEntity<LogisticsOrder> create(@RequestBody LogisticsOrder order) {
        return ResponseEntity.ok(service.createOrder(order));
    }

    @GetMapping
    @ResponseBody
    public List<LogisticsOrder> getAll(@RequestParam(required = false) OrderStatus status) {
        return (status != null) ? repo.findByCurrentStatus(status) : repo.findAll();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LogisticsOrder> update(@PathVariable Long id, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}