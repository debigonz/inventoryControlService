package com.inventory.products.controller;

import com.inventory.products.domain.entity.Product;
import com.inventory.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(Product product) {
        return ResponseEntity.status(201).body(productService.createProduct(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProduct(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product>  getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with ID " + id + " has been deleted");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Product>> getAllActiveProducts() {
        List<Product> products = productService.getActiveProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }


}
