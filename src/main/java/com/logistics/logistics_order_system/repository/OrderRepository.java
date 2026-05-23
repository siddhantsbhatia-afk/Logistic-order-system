package com.logistics.logistics_order_system.repository;

import com.logistics.logistics_order_system.model.LogisticsOrder;
import com.logistics.logistics_order_system.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<LogisticsOrder, Long> {
    // Fulfills Requirement #5: Duplicate Prevention
    boolean existsByCustomerNameAndPickupAddressAndDeliveryAddress(String name, String pickup, String delivery);

    // Fulfills Requirement #2: Filtering
    List<LogisticsOrder> findByCurrentStatus(OrderStatus status);
}