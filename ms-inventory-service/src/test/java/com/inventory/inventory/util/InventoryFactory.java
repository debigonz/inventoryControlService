package com.inventory.inventory.util;

import com.inventory.inventory.domain.dto.ProductDto;
import com.inventory.inventory.domain.entity.Inventory;
import com.inventory.inventory.domain.entity.Status;

public class InventoryFactory {

    public static Inventory inventoryTestOne() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setProductId(1L);
        inventory.setQuantity(100);
        inventory.setPrice(5.99);
        inventory.setCost(2.99);
        inventory.setCategory("Test Category");
        inventory.setStatus(Status.ACTIVE);
        return inventory;
    }

    public static Inventory inventoryTestTwo() {
        Inventory inventory = new Inventory();
        inventory.setId(2L);
        inventory.setProductId(2L);
        inventory.setQuantity(200);
        inventory.setPrice(8.99);
        inventory.setCost(4.99);
        inventory.setCategory("Test Category");
        inventory.setStatus(Status.ACTIVE);
        return inventory;
    }

    public static ProductDto productTestOne() {
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setName("Test Product One");
        product.setDescription("This is a test product description for product one.");
        return product;
    }

    public static ProductDto productTestTwo() {
        ProductDto product = new ProductDto();
        product.setId(2L);
        product.setName("Test Product Two");
        product.setDescription("This is a test product description for product two.");
        return product;
    }
}
