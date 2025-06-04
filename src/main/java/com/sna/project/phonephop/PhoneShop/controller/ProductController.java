package com.sna.project.phonephop.PhoneShop.controller;

import com.sna.project.phonephop.PhoneShop.Mapper.ProductMapper;
import com.sna.project.phonephop.PhoneShop.model.dto.ProductDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Product;
import com.sna.project.phonephop.PhoneShop.model.response.ApiResponse;
import com.sna.project.phonephop.PhoneShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(ApiResponse.<Product>builder()
                        .message("create product success")
                        .status(HttpStatus.ACCEPTED)
                        .payload(productService.createProduct(productDTO))
                        .timestamp(LocalDateTime.now())
                .build()
        );
    }
}
