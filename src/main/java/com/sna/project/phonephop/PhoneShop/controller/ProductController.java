package com.sna.project.phonephop.PhoneShop.controller;

import com.sna.project.phonephop.PhoneShop.Mapper.ProductMapper;
import com.sna.project.phonephop.PhoneShop.model.dto.PriceDTO;
import com.sna.project.phonephop.PhoneShop.model.dto.ProductDTO;
import com.sna.project.phonephop.PhoneShop.model.dto.ProductImportDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Product;
import com.sna.project.phonephop.PhoneShop.model.response.ApiResponse;
import com.sna.project.phonephop.PhoneShop.service.ProductService;
import io.swagger.models.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@RequestMapping("products")
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
    @PostMapping("/importProduct")
    ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO productImportDTO) {
        productService.importProduct(productImportDTO);
        return ResponseEntity.ok().build();
    }
    @PostMapping("{productId}/setSalePrice")
    public ResponseEntity<?> setSalePrice(@PathVariable Long productId,@RequestBody PriceDTO priceDTO) {
        productService.setSalePrice(productId,priceDTO.getPrice());
        return ResponseEntity.ok().build();
    }
    @PostMapping("uploadProduct")
    public ResponseEntity<?> uploadProduct(@RequestParam("file")  MultipartFile file) {
      Map<Integer,String> error=  productService.uploadProduct(file);
       return ResponseEntity.ok(error);
    }
}
