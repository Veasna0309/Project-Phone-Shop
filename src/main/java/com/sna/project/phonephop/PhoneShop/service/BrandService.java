package com.sna.project.phonephop.PhoneShop.service;

import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand findBrandById(Long id);
    Brand SaveBrand(BrandDTO brandDTO);
//    List<Brand> findAllBrands();
    Brand UpdateBrandById(Long id, BrandDTO brandDTO);
    String DeleteBrandById(Long id);
    List<Brand> filterByName(String name);
    List<Brand> findAllBrand(Map<String,String> param);
    Page<Brand> Pagination(Map<String,String> param);


}
