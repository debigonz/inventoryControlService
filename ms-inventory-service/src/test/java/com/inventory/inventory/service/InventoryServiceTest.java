package com.inventory.inventory.service;

import com.inventory.inventory.domain.dto.ProductDto;
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
        when(inventoryRepository.existsById(inventoryTestOne().getId())).thenReturn(true);

        // When
        Exception thrown = assertThrows(Exception.class, () -> inventoryService.createInventory(inventoryTestOne()));

        // Then
        assertNotNull(thrown);
        assertEquals("Inventory already exists", thrown.getMessage());
    }

    @Test
    void testGetProductsByCategorySuccessfully() {
        // Given
        when(productServiceClient.getProductsByCategory(productTestOne().getCategory()))
                .thenReturn(List.of(productTestOne(), productTestTwo()));

        // When
        List<ProductDto> response = inventoryService.getProductsByCategory(productTestOne().getCategory());

        // Then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void testGetProductQuantitySuccessfully() {
        // Given
        when(inventoryRepository.findById(inventoryTestOne().getId())).thenReturn(Optional.of(inventoryTestOne()));

        // When
        Integer response = inventoryService.getProductQuantity(inventoryTestOne().getId());

        // Then
        assertNotNull(response);
        assertEquals(inventoryTestOne().getQuantity(), response);
    }

    @Test
    void testGetProductQuantityFailed() {
        // Given
        when(inventoryRepository.findById(inventoryTestOne().getId())).thenReturn(Optional.empty());

        // When
        Exception thrown = assertThrows(Exception.class, () -> inventoryService.getProductQuantity(inventoryTestOne().getId()));

        // Then
        assertNotNull(thrown);
        assertEquals("Inventory not found with ID: " + inventoryTestOne().getId(), thrown.getMessage());
    }

    @Test
    void testGetProductsInStockSuccessfully() {
        // Given
        when(productServiceClient.getActiveProducts()).thenReturn(List.of(productTestOne(), productTestTwo()));
        when(inventoryRepository.findProductsWithStock()).thenReturn(List.of(inventoryTestOne(), inventoryTestTwo()));

        // When
        List<Inventory> response = inventoryService.getProductsInStock();

        // Then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(2, response.size());
    }

    @Test
    void testGetProductsInStockNoActiveProducts() {
        // Given
        when(productServiceClient.getActiveProducts()).thenReturn(List.of());

        // When
        List<Inventory> response = inventoryService.getProductsInStock();

        // Then
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    void testGetProductsInStockNoInventoriesWithStock() {
        // Given
        when(productServiceClient.getActiveProducts()).thenReturn(List.of(productTestOne(), productTestTwo()));
        when(inventoryRepository.findProductsWithStock()).thenReturn(List.of());

        // When
        List<Inventory> response = inventoryService.getProductsInStock();

        // Then
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    void testGetProductsInStockFail() {
        // Given
        when(productServiceClient.getActiveProducts()).thenThrow(new RuntimeException("Failed to retrieve active products"));

        // When
        Exception thrown = assertThrows(RuntimeException.class, () -> inventoryService.getProductsInStock());

        // Then
        assertNotNull(thrown);
    }

}
