package com.sna.project.phonephop.PhoneShop.service;

import com.sna.project.phonephop.PhoneShop.model.dto.ModelDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Model;

import java.util.List;

public interface ModelService {
    Model save(ModelDTO model);
    List<Model> getAllModels();
    Model findModelById(Long id);
    Model updaeModelById(Long id, ModelDTO modelDTO);
    String deleteModelById(Long id);
    List<Model> findModelByBrandId(Long brandId);
}
