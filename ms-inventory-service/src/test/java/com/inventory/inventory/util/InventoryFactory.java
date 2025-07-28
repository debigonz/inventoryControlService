package com.inventory.inventory.util;

import com.inventory.inventory.domain.dto.ProductDto;
import com.inventory.inventory.domain.entity.Inventory;

public class InventoryFactory {

    public static Inventory inventoryTestOne() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setProductId(1L);
        inventory.setQuantity(100);
        return inventory;
    }

    public static Inventory inventoryTestTwo() {
        Inventory inventory = new Inventory();
        inventory.setId(2L);
        inventory.setProductId(2L);
        inventory.setQuantity(200);
        return inventory;
    }

    public static ProductDto productTestOne() {
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setName("Test Product One");
        product.setDescription("This is a test product description for product one.");
        product.setPrice(5.99);
        product.setCost(2.99);
        product.setCategory("Test Category");
        product.setStatus("ACTIVE");
        return product;
    }

    public static ProductDto productTestTwo() {
        ProductDto product = new ProductDto();
        product.setId(2L);
        product.setName("Test Product Two");
        product.setDescription("This is a test product description for product two.");
        product.setPrice(8.99);
        product.setCost(4.99);
        product.setCategory("Test Category");
        product.setStatus("ACTIVE");
        return product;
    }
}
