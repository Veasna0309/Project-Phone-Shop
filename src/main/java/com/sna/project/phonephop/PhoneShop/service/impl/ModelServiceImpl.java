package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.Mapper.ModelMapper;
import com.sna.project.phonephop.PhoneShop.exception.ResourceNotFoundException;
import com.sna.project.phonephop.PhoneShop.model.dto.ModelDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import com.sna.project.phonephop.PhoneShop.model.entity.Model;
import com.sna.project.phonephop.PhoneShop.repository.ModelRepository;
import com.sna.project.phonephop.PhoneShop.service.BrandService;
import com.sna.project.phonephop.PhoneShop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final BrandService brandService;
    /*
    បើអត់ប្រើ modelMapper ទេ នោះ BrandService in interfaced MoelMapper will null
    because it don't have autowire
     */


    @Override
    public Model save(ModelDTO model) {
        Model models =modelMapper.toModel(model);
        return modelRepository.save(models);
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public Model findModelById(Long id) {

        return modelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Model",id));

    }

    @Override
    public Model updaeModelById(Long id, ModelDTO modelDTO) {
        var model=findModelById(id);
        if(model==null){
            throw new ResourceNotFoundException("Model",id);
        }
       Brand brand= brandService.findBrandById(modelDTO.getBrandId());
       model.setBrand(brand);
       model.setName(modelDTO.getName());
      return modelRepository.save(model);
    }

    @Override
    public String deleteModelById(Long id) {
      var model=findModelById(id);
      if(model==null){
          throw new ResourceNotFoundException("Model",id);
      }
      modelRepository.deleteById(id);
        return "model id:"+id+" has been  deleted";
    }

    @Override
    public List<Model> findModelByBrandId(Long brandId) {
        var brand=brandService.findBrandById(brandId);
        if(brand==null){
            throw new ResourceNotFoundException("Brand",brandId);
        }
       return  modelRepository.findModelsByBrandId(brandId);
    }
}
