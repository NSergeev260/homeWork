package com.example.objectMapper.api.controllers;

import com.example.objectMapper.usercasses.ProductService;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(
            @Valid @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto response = productService.addProduct(productRequestDto);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> response = productService.getAllProducts();
        return ResponseEntity
                .ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(
            @PathVariable UUID productId) {
        ProductResponseDto response = productService.getProduct(productId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable UUID productId,
            @Valid @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto response = productService.updateProduct(productId, productRequestDto);
        return ResponseEntity
                .ok(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.
                noContent().
                build();
    }
}

