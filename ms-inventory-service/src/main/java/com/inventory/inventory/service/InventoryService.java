package com.inventory.inventory.service;

import com.inventory.inventory.domain.dto.ProductDto;
import com.inventory.inventory.domain.entity.Inventory;
import com.inventory.inventory.domain.entity.Status;
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

    public Inventory createInventory(Inventory inventory) {
        log.info("Attempting to create inventory for product ID: {}", inventory.getProductId());

        // First, check if inventory for this product already exists to avoid duplicates.
        if (inventoryRepository.existsByProductId(inventory.getProductId())) {
            log.warn("Inventory for product ID {} already exists.", inventory.getProductId());
            throw new IllegalArgumentException("Inventory for product ID " + inventory.getProductId() + " already exists.");
        }

        try {
            // Call the product service using your Feign client to get the product details.
            // This is where `ProductServiceClient.getProductById` is executed.
            ProductDto product = productRepository.getProductById(inventory.getProductId());
            log.info("Verified product exists: {}", product.getName());

            // Use the ID from the product service response to ensure data integrity.
            inventory.setProductId(product.getId());

            // Set a default status and save the new inventory record to the database.
            inventory.setStatus(Status.ACTIVE);
            return inventoryRepository.save(inventory);

        } catch (Exception e) {
            log.error("Failed to create inventory. Product with ID {} not found or product service is unavailable.", inventory.getProductId(), e);
            throw new IllegalArgumentException("Could not create inventory. Product not found or service error.", e);
        }
    }

    public Inventory updateInventory(Long id, Inventory inventoryDetails) {
        log.info("Updating inventory for ID: {}", id);

        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + id));

        existingInventory.setQuantity(inventoryDetails.getQuantity());
        existingInventory.setPrice(inventoryDetails.getPrice());
        existingInventory.setCost(inventoryDetails.getCost());
        existingInventory.setCategory(inventoryDetails.getCategory());
        existingInventory.setStatus(inventoryDetails.getStatus());

        return inventoryRepository.save(existingInventory);
    }

    public List<Inventory> getProductsByCategory(String category) {
        log.info("Finding products by category: {}", category);
        return inventoryRepository.findByCategory(category);
    }

    public List<Inventory> getAllInventories() {
        log.info("Finding all inventories");
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        log.info("Finding inventory by ID: {}", id);
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + id));
    }

    public List<Inventory> getProductsInStock() {
        log.info("Finding active products with stock greater than 0");
        try {
            List<Inventory> productsInStock = inventoryRepository.findByStatusAndQuantityGreaterThan(Status.ACTIVE, 0);
            log.info("Found {} active products with stock", productsInStock.size());
            return productsInStock;
        } catch (Exception e) {
            log.error("An error occurred while finding products in stock", e);
            throw new IllegalArgumentException("Failed to retrieve products in stock", e);
        }
    }

    public void deleteInventory(Long id) {
        log.info("Deleting inventory with ID: {}", id);
        if (!inventoryRepository.existsById(id)) {
            log.error("Inventory with ID {} not found", id);
            throw new IllegalArgumentException("Inventory not found");
        }
        inventoryRepository.deleteById(id);
    }
}
