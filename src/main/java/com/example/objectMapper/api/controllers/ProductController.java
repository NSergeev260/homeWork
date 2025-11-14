package com.example.objectMapper.api.controllers;

import com.example.objectMapper.usercasses.ProductService;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('MODERATOR', 'SUPER_ADMIN')")
    public ResponseEntity<String> addProduct(@RequestBody String productJson)
            throws JsonProcessingException {

        ProductRequestDto requestDto = objectMapper.readValue(
                productJson, ProductRequestDto.class);
        ProductResponseDto responseDto = productService.addProduct(requestDto);
        String responseJson = objectMapper.writeValueAsString(responseDto);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(responseJson);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException {
        List<ProductResponseDto> products = productService.getAllProducts();
        String productsJson = objectMapper.writeValueAsString(products);

        return ResponseEntity.
                ok(productsJson);
    }

    @GetMapping("/{productId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getProduct(@PathVariable UUID productId)
            throws JsonProcessingException {

        ProductResponseDto product = productService.getProduct(productId);
        String productJson = objectMapper.writeValueAsString(product);

        return ResponseEntity.
                ok(productJson);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAnyRole('MODERATOR', 'SUPER_ADMIN')")
    public ResponseEntity<String> updateProduct(
            @PathVariable UUID productId,
            @RequestBody String productJson) throws JsonProcessingException {

        ProductRequestDto requestDto = objectMapper.
                readValue(productJson, ProductRequestDto.class);
        ProductResponseDto responseDto = productService.updateProduct(productId, requestDto);
        String responseJson = objectMapper.writeValueAsString(responseDto);

        return ResponseEntity.
                ok(responseJson);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.
                noContent().
                build();
    }
}