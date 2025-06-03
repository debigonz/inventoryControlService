package com.inventory.products.domain.repository;

import com.inventory.products.domain.entity.Product;
import com.inventory.products.domain.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByStatus(Status status);
    Product findProductByName(String productName);

}
