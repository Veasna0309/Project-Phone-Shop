package com.sna.project.phonephop.PhoneShop.service;

import com.sna.project.phonephop.PhoneShop.model.dto.ProductDTO;
import com.sna.project.phonephop.PhoneShop.model.dto.ProductImportDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Product;
import com.sna.project.phonephop.PhoneShop.model.entity.ProductImportHistory;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product getById(Long id);
    Product getByModelIdAndColorId(Long modelId, Long colorId);
    void importProduct(ProductImportDTO productImportDTO);
    void  setSalePrice(Long productId, BigDecimal salePrice);
    void validateStock(Long productId,Integer numberOfUnit);
    Map<Integer,String> uploadProduct(MultipartFile file);
}
