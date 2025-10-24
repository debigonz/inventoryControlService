package com.inventory.products.utils;

import com.inventory.products.domain.entity.Product;

public class ProductFactory {

    public static Product productTestOne() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product One");
        product.setDescription("This is a test product description for product one.");
        return product;
    }

    public static Product productTestTwo() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Test Product Two");
        product.setDescription("This is a test product description for product two.");
        return product;
    }
}
