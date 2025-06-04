package com.sna.project.phonephop.PhoneShop.service;

import com.sna.project.phonephop.PhoneShop.model.dto.ProductDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Product;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product getById(Long id);
}
