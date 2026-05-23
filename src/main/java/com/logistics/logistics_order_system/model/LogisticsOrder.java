package com.logistics.logistics_order_system.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class LogisticsOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String pickupAddress;
    private String deliveryAddress;
    private Double weight;
    private String priority;

    @Enumerated(EnumType.STRING)
    private OrderStatus currentStatus = OrderStatus.CREATED;
}