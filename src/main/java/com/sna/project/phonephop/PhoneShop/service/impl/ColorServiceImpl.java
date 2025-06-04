package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.exception.ResourceNotFoundException;
import com.sna.project.phonephop.PhoneShop.model.entity.Color;
import com.sna.project.phonephop.PhoneShop.repository.ColorRepository;
import com.sna.project.phonephop.PhoneShop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
     private final ColorRepository colorRepository;
    @Override
    public Color createColor(Color color) {
        return null;
    }

    @Override
    public Color getColorById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("color",id));
    }
}
