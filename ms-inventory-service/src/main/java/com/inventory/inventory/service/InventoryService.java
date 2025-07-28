package com.inventory.inventory.service;

import com.inventory.inventory.domain.dto.ProductDto;
import com.inventory.inventory.domain.entity.Inventory;
import com.inventory.inventory.domain.repository.InventoryRepository;
import com.inventory.inventory.domain.repository.ProductServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;
    private ProductServiceClient productRepository;

    public List<ProductDto> getProductsByCategory(String category) {
        log.info("Finding products by category: {}", category);
        return productRepository.getProductsByCategory(category);
    }

    public Inventory createInventory(Inventory inventory) {
        log.info("Creating inventory: {}", inventory);
        if (inventoryRepository.existsById(inventory.getId())) {
            log.error("Inventory with ID {} already exists", inventory.getId());
            throw new IllegalArgumentException("Inventory already exists");
        }
        return inventoryRepository.save(inventory);
    }

    public Integer getProductQuantity(Long id) {
        log.info("Getting stock for product ID: {}", id);
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + id));
        return inventory.getQuantity();
    }

    public List<Inventory> getProductsInStock() {
        log.info("Finding active products with stock greater than 0");

        try {
            // Obtener productos activos
            List<ProductDto> activeProducts = productRepository.getActiveProducts();
            if (activeProducts == null || activeProducts.isEmpty()) {
                log.warn("No active products found");
                return Collections.emptyList();
            }

            List<Inventory> inventoriesWithStock = inventoryRepository.findProductsWithStock();
            if (inventoriesWithStock == null || inventoriesWithStock.isEmpty()) {
                log.warn("No inventories with stock found");
                return Collections.emptyList();
            }

            List<Long> activeProductIds = activeProducts.stream()
                    .map(ProductDto::getId)
                    .toList();

            List<Inventory> filteredInventories = inventoriesWithStock.stream()
                    .filter(inventory -> activeProductIds.contains(inventory.getProductId()))
                    .toList();

            log.info("Found {} products in stock", filteredInventories.size());
            return filteredInventories;

        } catch (Exception e) {
            log.error("An error occurred while finding products in stock: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve products in stock", e);
        }
    }

}
