package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.entity.Brand;
import com.sna.project.phonephop.PhoneShop.repository.BrandRepository;
import com.sna.project.phonephop.PhoneShop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.image.ImagingOpException;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public Brand findBrandById(Integer id) {
        return brandRepository.findById(id).orElse(null);
    }
}
