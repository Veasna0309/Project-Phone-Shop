package com.sna.project.phonephop.PhoneShop.Mapper;

import com.sna.project.phonephop.PhoneShop.model.dto.ModelDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Model;
import com.sna.project.phonephop.PhoneShop.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BrandService.class}) //ដាក់spring ដើម្បី autowire brandservice    , ដាក់BrandService.class ដើម្បreuse method findByID(Long id)
//ហើយយើងត្រូវ autowire ModelMapper ក្នុង ModelController   បើមិនទេBrandSerice ក្នុង ModelMapper នឹងnull
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
    @Mapping(target = "brand",source = "brandId")
    Model toModel(ModelDTO modelDTO);
    @Mapping(target = "brandId",source = "brand.id")
    ModelDTO toModelDTO(Model model);

//    default Brand toBrand(Long brandid){
//        Brand brand = new Brand();
//        brand.setId(brandid);
//        return brand;
//    }

}
