package com.devcaiqueoliveira.mercatopdvsystem.controller;

import com.devcaiqueoliveira.mercatopdvsystem.controller.dto.ProductResponse;
import com.devcaiqueoliveira.mercatopdvsystem.entity.Product;
import com.devcaiqueoliveira.mercatopdvsystem.mapper.CategoryMapper;
import com.devcaiqueoliveira.mercatopdvsystem.mapper.ProductMapper;
import com.devcaiqueoliveira.mercatopdvsystem.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listAllProducts() {
        List<Product> products = service.listAll();

        List<ProductResponse> responses = products.stream()
                .map(ProductMapper::toProductResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Product product = service.findById(id);
        return ResponseEntity.ok(ProductMapper.toProductResponse(product));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = service.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}