package com.inventory.products.service;

import com.inventory.products.domain.entity.Product;
import com.inventory.products.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.inventory.products.utils.ProductFactory.productTestOne;
import static com.inventory.products.utils.ProductFactory.productTestTwo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void testProductCreatedSuccessfully() {
        // Given
        when(productRepository.findById(productTestOne().getId())).thenReturn(Optional.empty());
        when(productRepository.save(productTestOne())).thenReturn(productTestOne());

        // When
        Product response = productService.createProduct(productTestOne());

        // Then
        assertNotNull(response);
        assertEquals(productTestOne().getId(), response.getId());
    }

    @Test
    void testProductCreatedFailed() {
        // Given
        when(productRepository.findById(productTestOne().getId())).thenReturn(Optional.of(productTestOne()));
        when(productRepository.findProductByName(productTestOne().getName())).thenReturn(productTestOne());

        // When
        Exception thrown = assertThrows(Exception.class, () -> productService.createProduct(productTestOne()));

        // Then
        assertNotNull(thrown);
        assertEquals("Product already exists", thrown.getMessage());
        verify(productRepository).findProductByName(productTestOne().getName());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testProductUpdateSuccessfully() {
        // Given
        when(productRepository.findById(productTestTwo().getId())).thenReturn(Optional.of(productTestTwo()));
        when(productRepository.save(productTestTwo())).thenReturn(productTestTwo());

        // When
        Product response = productService.updateProduct(productTestTwo().getId());

        //Then
        assertNotNull(response);
        assertEquals(productTestTwo().getId(), response.getId());
    }

    @Test
    void testProductUpdateFailed() {
        // Given
        when(productRepository.findById(productTestTwo().getId())).thenReturn(Optional.empty());

        // When
        Exception thrown = assertThrows(Exception.class, () -> productService.updateProduct(productTestTwo().getId()));

        // Then
        assertNotNull(thrown);
        assertEquals("Product not found with ID: " + productTestTwo().getId(), thrown.getMessage());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testGetProductByIdSuccessfully() {
        // Given
        when(productRepository.findById(productTestOne().getId())).thenReturn(Optional.of(productTestOne()));

        // When
        Product response = productService.getProductById(productTestOne().getId());

        // Then
        assertNotNull(response);
        assertEquals(productTestOne().getId(), response.getId());
    }

    @Test
    void testGetProductByIdFailed() {
        // Given
        when(productRepository.findById(productTestOne().getId())).thenReturn(Optional.empty());

        // When
        Exception thrown = assertThrows(Exception.class, () -> productService.getProductById(productTestOne().getId()));

        // Then
        assertNotNull(thrown);
        assertEquals("Product not found with ID: " + productTestOne().getId(), thrown.getMessage());
    }

    @Test
    void testGetAllProductsSuccessfully() {
        // Given
        when(productRepository.findAll()).thenReturn(List.of(productTestOne(), productTestTwo()));

        // When
        List<Product> response = productService.getAllProducts();

        // Then
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void testDeleteProductSuccessfully() {
        // Given
        when(productRepository.existsById(productTestTwo().getId())).thenReturn(true);

        // When
        productService.deleteProduct(productTestTwo().getId());

        // Then
        verify(productRepository).existsById(productTestTwo().getId());
        verify(productRepository).deleteById(productTestTwo().getId());
    }

    @Test
    void testDeleteProductFailed() {
        // Given
        when(productRepository.existsById(productTestTwo().getId())).thenReturn(false);

        // When
        Exception thrown = assertThrows(Exception.class, () -> productService.deleteProduct(productTestTwo().getId()));
        // Then
        assertNotNull(thrown);
        assertEquals("Product not found", thrown.getMessage());
        verify(productRepository).existsById(productTestTwo().getId());
        verify(productRepository, never()).deleteById(anyLong());
    }

    @Test
    void testGetProductsByCategorySuccessfully() {
        // Given
        String category = "Test Category";
        when(productRepository.findByCategory(category)).thenReturn(List.of(productTestOne(), productTestTwo()));

        // When
        List<Product> response = productService.getProductsByCategory(category);

        // Then
        assertNotNull(response);
        assertEquals(2, response.size());
    }
}
