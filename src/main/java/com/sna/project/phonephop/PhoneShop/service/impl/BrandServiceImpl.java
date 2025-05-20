package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.Mapper.Mapper;
import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import com.sna.project.phonephop.PhoneShop.repository.BrandRepository;
import com.sna.project.phonephop.PhoneShop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public Brand findBrandById(Integer id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public Brand SaveBrand(BrandDTO brandDTO) {
       Brand brand= Mapper.toBrand(brandDTO);
       return brandRepository.save(brand);
    }

    @Override
    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand UpdateBrandById(Integer id, BrandDTO brandDTO) {
        var brand= findBrandById(id);
        if(brand!=null){
            brand.setName(brandDTO.getName());
        }
        return brandRepository.save(brand);
    }

    @Override
    public String DeleteBrandById(Integer id) {
        var brand = findBrandById(id);
        if(brand!=null){
            brandRepository.deleteById(id);
            return "id: "+ id+ " was  delete Successfully";
        }
        return "Brand not found";

    }
}
