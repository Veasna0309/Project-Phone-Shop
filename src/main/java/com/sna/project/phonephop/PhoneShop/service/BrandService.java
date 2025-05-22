package com.sna.project.phonephop.PhoneShop.service;

import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand findBrandById(Integer id);
    Brand SaveBrand(BrandDTO brandDTO);
//    List<Brand> findAllBrands();
    Brand UpdateBrandById(Integer id, BrandDTO brandDTO);
    String DeleteBrandById(Integer id);
    List<Brand> filterByName(String name);
    List<Brand> findAllBrand(Map<String,String> param);


}
