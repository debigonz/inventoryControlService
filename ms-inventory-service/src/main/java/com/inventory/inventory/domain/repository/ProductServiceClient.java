package com.inventory.inventory.domain.repository;

import com.inventory.inventory.domain.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-products", url = "${ms-products.url}")
public interface ProductServiceClient {

    @GetMapping("products/{id}")
    ProductDto getProductById(@PathVariable Long id);

}
