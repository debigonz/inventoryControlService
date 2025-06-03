package com.inventory.products.service;

import com.inventory.products.domain.entity.Product;
import com.inventory.products.domain.entity.Status;
import com.inventory.products.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        if (productRepository.findById(product.getId()).isPresent() && productRepository.findProductByName(product.getName()) != null) {
            log.error("Product with ID {} already exists", product.getId());
            throw new IllegalArgumentException("Product already exists");
        }
        log.info("Creating product: {}", product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            log.error("Product with ID {} not found", id);
            throw new IllegalArgumentException("Product not found");
        }
        product.setId(id);
        log.info("Updating product: {}", product);
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        log.info("Find product by ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    public List<Product> getAllProducts() {
        log.info("Finding all products");
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            log.error("Product with ID {} not found", id);
            throw new IllegalArgumentException("Product not found");
        }
        log.info("Deleting product with ID {}", id);
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        log.info("Finding products by category: {}", category);
        return productRepository.findByCategory(category);
    }

    public Integer getProductQuantity(Long id) {
        log.info("Getting stock for product ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        return product.getStock();
    }

    public List<Product> getProductsInStock() {
        log.info("Finding active products with stock greater than 0");
        return productRepository.findByStatus(Status.ACTIVE).stream()
                .filter(product -> product.getStock() > 0)
                .toList();
    }

}
