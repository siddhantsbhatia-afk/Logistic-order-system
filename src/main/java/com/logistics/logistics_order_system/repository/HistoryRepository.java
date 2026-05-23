package com.logistics.logistics_order_system.repository;

import com.logistics.logistics_order_system.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<StatusHistory, Long> {
}