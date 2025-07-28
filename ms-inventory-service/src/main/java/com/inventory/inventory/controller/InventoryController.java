package com.inventory.inventory.controller;

import com.inventory.inventory.domain.entity.Inventory;
import com.inventory.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private InventoryService inventoryService;

    @GetMapping("/stock/{id}")
    public ResponseEntity<Integer> getProductStock(@PathVariable Long id) {
        Integer product = inventoryService.getProductQuantity(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Inventory>> getProductsInStock() {
        List<Inventory> productsInStock = inventoryService.getProductsInStock();
        return ResponseEntity.ok(productsInStock);
    }
}
