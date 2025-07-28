package com.inventory.products.utils;

import com.inventory.products.domain.entity.Product;
import com.inventory.products.domain.entity.Status;

public class ProductFactory {

    public static Product productTestOne() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product One");
        product.setDescription("This is a test product description for product one.");
        product.setPrice(5.99);
        product.setCost(2.99);
        product.setCategory("Test Category");
        product.setStatus(Status.ACTIVE);
        return product;
    }

    public static Product productTestTwo() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Test Product Two");
        product.setDescription("This is a test product description for product two.");
        product.setPrice(8.99);
        product.setCost(4.99);
        product.setCategory("Test Category");
        product.setStatus(Status.ACTIVE);
        return product;
    }
}
