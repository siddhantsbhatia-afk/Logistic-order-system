package com.logistics.logistics_order_system.service;

import com.logistics.logistics_order_system.model.*;
import com.logistics.logistics_order_system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogisticsService {

    private final OrderRepository orderRepo;
    private final HistoryRepository historyRepo;

    public LogisticsOrder createOrder(LogisticsOrder order) {
        // Requirement #5: Duplicate Prevention based on Customer + Pickup + Delivery [cite: 15]
        if (orderRepo.existsByCustomerNameAndPickupAddressAndDeliveryAddress(
                order.getCustomerName(), order.getPickupAddress(), order.getDeliveryAddress())) {
            throw new RuntimeException("Order already exists for this customer and route.");
        }
        LogisticsOrder savedOrder = orderRepo.save(order);
        saveHistory(savedOrder.getId(), savedOrder.getCurrentStatus());
        return savedOrder;
    }

    public LogisticsOrder updateStatus(Long id, OrderStatus nextStatus) {
        LogisticsOrder order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderStatus current = order.getCurrentStatus();

        // Requirement #3: Status Lifecycle Rules (No skipping, no backward) [cite: 11]
        boolean isValid = switch (current) {
            case CREATED -> nextStatus == OrderStatus.PICKED_UP;
            case PICKED_UP -> nextStatus == OrderStatus.IN_TRANSIT;
            case IN_TRANSIT -> nextStatus == OrderStatus.DELIVERED;
            case DELIVERED -> false; // Final state
        };

        if (!isValid) {
            throw new RuntimeException("Invalid status transition from " + current + " to " + nextStatus);
        }

        order.setCurrentStatus(nextStatus);
        saveHistory(id, nextStatus); // Requirement #4: History logging [cite: 13]
        return orderRepo.save(order);
    }

    private void saveHistory(Long orderId, OrderStatus status) {
        StatusHistory history = new StatusHistory();
        history.setOrderId(orderId);
        history.setStatus(status); // Pass the enum directly
        historyRepo.save(history);
    }
}