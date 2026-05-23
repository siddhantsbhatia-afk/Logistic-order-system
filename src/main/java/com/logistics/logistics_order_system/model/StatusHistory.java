package com.logistics.logistics_order_system.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class StatusHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;

    @Enumerated(EnumType.STRING) // This tells Hibernate to store the text 'CREATED' instead of a number
    private OrderStatus status;

    private LocalDateTime timestamp = LocalDateTime.now();
}