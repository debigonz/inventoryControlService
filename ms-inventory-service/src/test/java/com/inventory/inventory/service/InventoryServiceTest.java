package com.inventory.inventory.service;

import com.inventory.inventory.domain.entity.Inventory;
import com.inventory.inventory.domain.repository.InventoryRepository;
import com.inventory.inventory.domain.repository.ProductServiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.inventory.inventory.util.InventoryFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    InventoryRepository inventoryRepository;
    @Mock
    ProductServiceClient productServiceClient;
    @InjectMocks
    InventoryService inventoryService;

    @Test
    void testCreateInventorySuccessfully() {
        // Given
        when(productServiceClient.getProductById(inventoryTestOne().getProductId()))
                .thenReturn(productTestOne());
        when(inventoryRepository.save(any())).thenReturn(inventoryTestOne());

        // When
        Inventory response = inventoryService.createInventory(inventoryTestOne());

        // Then
        assertNotNull(response);
        assertEquals(inventoryTestOne().getId(), response.getId());
    }

    @Test
    void testCreateInventoryFailure() {
        // Given
        when(productServiceClient.getProductById(inventoryTestOne().getProductId())).thenReturn(productTestOne());
        when(inventoryRepository.save(inventoryTestOne())).thenThrow(new IllegalArgumentException("Could not create inventory. Product not found or service error."));

        // When
        Exception thrown = assertThrows(Exception.class, () -> inventoryService.createInventory(inventoryTestOne()));

        // Then
        assertNotNull(thrown);
        assertEquals("Could not create inventory. Product not found or service error.", thrown.getMessage());
    }

    @Test
    void testUpdateInventorySuccessfully() {
        // Given
        when(inventoryRepository.findById(inventoryTestOne().getId())).thenReturn(Optional.of(inventoryTestOne()));
        when(inventoryRepository.save(any())).thenReturn(inventoryTestTwo());

        // When
        Inventory response = inventoryService.updateInventory(inventoryTestOne().getId(), inventoryTestTwo());

        // Then
        assertNotNull(response);
        assertEquals(inventoryTestTwo().getId(), response.getId());
    }

    @Test
    void testUpdateInventoryFailure() {
        // Given
        when(inventoryRepository.findById(inventoryTestOne().getId())).thenReturn(Optional.empty());

        // When
        Exception thrown = assertThrows(Exception.class, () -> inventoryService.updateInventory(inventoryTestOne().getId(), inventoryTestTwo()));

        // Then
        assertNotNull(thrown);
        assertEquals("Inventory not found with ID: " + inventoryTestOne().getId(), thrown.getMessage());
    }

    @Test
    void testGetProductsByCategorySuccessfully() {
        // Given
        when(inventoryRepository.findByCategory(inventoryTestOne().getCategory()))
                .thenReturn(List.of(inventoryTestOne(), inventoryTestTwo()));

        // When
        List<Inventory> response = inventoryService.getProductsByCategory(inventoryTestOne().getCategory());

        // Then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void testGetProductsInStockSuccessfully() {
        // Given
        when(inventoryRepository.findByStatusAndQuantityGreaterThan(any(), any())).thenReturn(List.of(inventoryTestOne(), inventoryTestTwo()));

        // When
        List<Inventory> response = inventoryService.getProductsInStock();

        // Then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void testGetProductsInStockFail() {
        // Given
        when(inventoryRepository.findByStatusAndQuantityGreaterThan(any(), any())).thenThrow(new IllegalArgumentException("Failed to retrieve products in stock"));

        // When
        Exception thrown = assertThrows(Exception.class, () -> inventoryService.getProductsInStock());

        // Then
        assertNotNull(thrown);
    }

    @Test
    void testGetAllInventoriesSuccessfully() {
        // Given
        when(inventoryRepository.findAll()).thenReturn(List.of(inventoryTestOne(), inventoryTestTwo()));

        // When
        List<Inventory> response = inventoryService.getAllInventories();

        // Then
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void testGetByIdSuccessfully() {
        // Given
        when(inventoryRepository.findById(inventoryTestOne().getId())).thenReturn(Optional.of(inventoryTestOne()));

        // When
        Inventory response = inventoryService.getInventoryById(inventoryTestOne().getId());

        // Then
        assertNotNull(response);
        assertEquals(inventoryTestOne().getId(), response.getId());
    }

    @Test
    void testGetByIdFailure() {
        // Given
        when(inventoryRepository.findById(inventoryTestOne().getId())).thenReturn(Optional.empty());

        // When
        Exception thrown = assertThrows(Exception.class, () -> inventoryService.getInventoryById(inventoryTestOne().getId()));

        // Then
        assertNotNull(thrown);
        assertEquals("Inventory not found with ID: " + inventoryTestOne().getId(), thrown.getMessage());
    }

    @Test
    void testDeleteInventorySuccessfully() {
        // Given
        when(inventoryRepository.existsById(inventoryTestOne().getId())).thenReturn(true);

        // When
        assertDoesNotThrow(() -> inventoryService.deleteInventory(inventoryTestOne().getId()));
    }

    @Test
    void testDeleteInventoryFailure() {
        // Given
        when(inventoryRepository.existsById(inventoryTestOne().getId())).thenReturn(false);

        // When
        Exception thrown = assertThrows(Exception.class, () -> inventoryService.deleteInventory(inventoryTestOne().getId()));

        // Then
        assertNotNull(thrown);
        assertEquals("Inventory not found", thrown.getMessage());
    }

}
