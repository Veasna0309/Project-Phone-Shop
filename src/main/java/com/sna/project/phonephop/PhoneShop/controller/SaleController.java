package com.sna.project.phonephop.PhoneShop.controller;

import com.sna.project.phonephop.PhoneShop.model.dto.SaleDTO;
import com.sna.project.phonephop.PhoneShop.service.ProductService;
import com.sna.project.phonephop.PhoneShop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("sales")
public class SaleController {
    private final SaleService saleService;
    @PostMapping
    public ResponseEntity creat(@RequestBody SaleDTO saleDTO) {
        saleService.sell(saleDTO);
        return ResponseEntity.ok().build();
    }



}
