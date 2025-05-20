package com.sna.project.phonephop.PhoneShop.service.util;

import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;

public class Mapper {

    public static Brand toBrand(BrandDTO brandDTO) {

        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        return brand;
    }
    public static BrandDTO toBrandDTO(Brand brand) {
        BrandDTO brandDTO=new BrandDTO();
        brandDTO.setName(brand.getName());
        return brandDTO;
    }


}
