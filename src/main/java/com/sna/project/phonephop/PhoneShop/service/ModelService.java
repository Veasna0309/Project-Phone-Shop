package com.sna.project.phonephop.PhoneShop.service;

import com.sna.project.phonephop.PhoneShop.model.dto.ModelDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Model;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ModelService {
    Model save(ModelDTO model);
    List<Model> getAllModels();
    Model findModelById(Long id);
    Model updaeModelById(Long id, ModelDTO modelDTO);
    String deleteModelById(Long id);
    List<Model> findModelByBrandId(Long brandId);
    List<Model> findModelByIdAndName(Map<String,String> params);
    Page<Model> PaginationModel(Map<String,String> param);

}
