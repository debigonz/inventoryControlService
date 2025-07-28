package com.inventory.inventory.domain.repository;

import com.inventory.inventory.domain.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-products", url = "${ms-products.url}")
public interface ProductServiceClient {

    @GetMapping("products/category/{category}")
    List<ProductDto> getProductsByCategory(@PathVariable String category);

    @GetMapping("products/{id}")
    ProductDto getProductById(@PathVariable Long id);

    @GetMapping("products/active")
    List<ProductDto> getActiveProducts();


}
