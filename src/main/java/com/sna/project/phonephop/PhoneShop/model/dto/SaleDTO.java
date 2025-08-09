package com.sna.project.phonephop.PhoneShop.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {
    private List<ProductSoldDTO> products;
    private LocalDate saleDate;
}
