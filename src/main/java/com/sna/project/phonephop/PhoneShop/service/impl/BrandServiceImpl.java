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
import com.sna.project.phonephop.PhoneShop.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Brand> Pagination(Map<String, String> param) {
        BrandFilter brandFilter = new BrandFilter();
        if(param.containsKey("name")){
            brandFilter.setName(param.get("name"));
        }
        if(param.containsKey("id")){
            brandFilter.setId(Integer.parseInt(param.get("id")));
        }

        int pageLimit=PageUtil.DEFUALT_PAGE_lIMITE;
        if(param.containsKey(PageUtil.PAGE_LINIT)){
            pageLimit=Integer.parseInt(param.get(PageUtil.PAGE_LINIT));
        }
        int pageNumber=PageUtil.DEFUALT_PAGE_NUMBER;
        if(param.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber=Integer.parseInt(param.get(PageUtil.PAGE_NUMBER));
        }


        BrandSpec brandSpec=new BrandSpec(brandFilter);
        Pageable pageable=PageUtil.getPageable(pageNumber,pageLimit);

        Page<Brand> page=brandRepository.findAll(brandSpec,pageable);

        return page;
    }


}
