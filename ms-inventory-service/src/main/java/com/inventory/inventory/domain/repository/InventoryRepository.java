package com.inventory.inventory.domain.repository;

import com.inventory.inventory.domain.entity.Inventory;
import com.inventory.inventory.domain.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByCategory(String category);
    List<Inventory> findByStatusAndQuantityGreaterThan(Status status, Integer quantity);
    boolean existsByProductId(Long productId);
}
