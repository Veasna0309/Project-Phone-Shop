package com.sna.project.phonephop.PhoneShop.Mapper;

import com.sna.project.phonephop.PhoneShop.model.dto.ProductDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Product;
import com.sna.project.phonephop.PhoneShop.service.ColorService;
import com.sna.project.phonephop.PhoneShop.service.ModelService;
import com.sna.project.phonephop.PhoneShop.service.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
    //យើងត្រូវconvert modelId to model តាមរយៈ uses = {ProductService.class}ព្រោះក្នុងនោះមានmethod getByIdដែលបោះreturn Model
    @Mapping(target = "model",source = "modelId")
    @Mapping(target = "color",source = "colorId")
    Product toProduct(ProductDTO productDTO);

}
