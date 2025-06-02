package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.Mapper.ModelEntityMapper;
import com.sna.project.phonephop.PhoneShop.exception.ResourceNotFoundException;
import com.sna.project.phonephop.PhoneShop.model.dto.ModelDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import com.sna.project.phonephop.PhoneShop.model.entity.Model;
import com.sna.project.phonephop.PhoneShop.repository.ModelRepository;
import com.sna.project.phonephop.PhoneShop.service.BrandService;
import com.sna.project.phonephop.PhoneShop.service.ModelService;
import com.sna.project.phonephop.PhoneShop.spec.ModelFilter;
import com.sna.project.phonephop.PhoneShop.spec.ModelSpec;
import com.sna.project.phonephop.PhoneShop.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelEntityMapper modelMapper;
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
       return  modelRepository.findByBrandId(brandId);
    }

    @Override
    public List<Model> findModelByIdAndName(Map<String, String> param) {
        ModelFilter modelFilter=new ModelFilter();

        if(param.containsKey("id")){
            modelFilter.setId(Long.parseLong(param.get("id")));
        }
        if(param.containsKey("name")){
            modelFilter.setName(param.get("name"));
        }

        ModelSpec modelSpec=new ModelSpec(modelFilter);
        return modelRepository.findAll(modelSpec);
    }

    @Override
    public Page<Model> PaginationModel(Map<String, String> param) {
        int pageLimit = PageUtil.DEFUALT_PAGE_lIMITE;
        if (param.containsKey(PageUtil.PAGE_LINIT)) {
            pageLimit = Integer.parseInt(param.get(PageUtil.PAGE_LINIT));
        }

        int pageNumber = PageUtil.DEFUALT_PAGE_NUMBER;
        if (param.containsKey(PageUtil.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(param.get(PageUtil.PAGE_NUMBER));
        }
        Pageable pageable=PageUtil.getPageable(pageNumber,pageLimit);
        return modelRepository.findAll(pageable);
    }

}
