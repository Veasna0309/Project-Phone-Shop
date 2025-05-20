package com.sna.project.phonephop.PhoneShop.Mapper;

import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    public Brand toBrand(BrandDTO brandDTO) ;
    public BrandDTO toBrandDTO(Brand brand) ;
}
