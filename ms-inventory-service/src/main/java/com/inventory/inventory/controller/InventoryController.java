package com.inventory.inventory.controller;

import com.inventory.inventory.domain.entity.Inventory;
import com.inventory.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory createdInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.status(201).body(createdInventory);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
        return ResponseEntity.ok(updatedInventory);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAll() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        if (inventories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Inventory>> getProductsInStock() {
        List<Inventory> productsInStock = inventoryService.getProductsInStock();
        return ResponseEntity.ok(productsInStock);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Inventory>> getAllProductsByCategory(@PathVariable String category) {
        List<Inventory> products = inventoryService.getProductsByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInventoryById(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }
}
