package com.sna.project.phonephop.PhoneShop.service;

import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand findBrandById(Integer id);
    Brand SaveBrand(BrandDTO brandDTO);
    List<Brand> findAllBrands();
    Brand UpdateBrandById(Integer id, BrandDTO brandDTO);
    String DeleteBrandById(Integer id);
}
