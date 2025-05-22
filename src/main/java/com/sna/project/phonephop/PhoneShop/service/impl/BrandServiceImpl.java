package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.Mapper.BrandMapper;
import com.sna.project.phonephop.PhoneShop.service.util.Mapper;
import com.sna.project.phonephop.PhoneShop.exception.ResourceNotFoundException;
import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import com.sna.project.phonephop.PhoneShop.repository.BrandRepository;
import com.sna.project.phonephop.PhoneShop.service.BrandService;
import com.sna.project.phonephop.PhoneShop.spec.BrandFilter;
import com.sna.project.phonephop.PhoneShop.spec.BrandSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public Brand findBrandById(Integer id) {
        return brandRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Brand",id));
    }

    @Override
    public Brand SaveBrand(BrandDTO brandDTO) {
      Brand brand= BrandMapper.INSTANCE.toBrand(brandDTO);
       return brandRepository.save(brand);
    }

    @Override
    public Brand UpdateBrandById(Integer id, BrandDTO brandDTO) {
        var brand= findBrandById(id);
        if(brand==null){
            throw new ResourceNotFoundException("Brand",id);
        }
        brand.setName(brandDTO.getName());
        return brandRepository.save(brand);
    }
    @Override
    public String DeleteBrandById(Integer id) {
        var brand = findBrandById(id);
        if(brand==null){
            throw new ResourceNotFoundException("Brand",id);
        }
        brandRepository.deleteById(id);
        return "id: "+ id+ " was  delete Successfully";
    }


    @Override
    public List<Brand> filterByName(String name) {
        return brandRepository.findByNameContaining(name);
    }

    @Override
    public List<Brand> findAllBrand(Map<String, String> param) {

        BrandFilter brandFilter = new BrandFilter();
        if(param.containsKey("name")){
            brandFilter.setName(param.get("name"));
        }
        if(param.containsKey("id")){
            brandFilter.setId(Integer.parseInt(param.get("id")));
        }

        BrandSpec brandSpec=new BrandSpec(brandFilter);
        return brandRepository.findAll(brandSpec);
    }

}
