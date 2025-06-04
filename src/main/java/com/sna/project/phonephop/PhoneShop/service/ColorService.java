package com.sna.project.phonephop.PhoneShop.service;

import com.sna.project.phonephop.PhoneShop.model.entity.Color;

public interface ColorService {
    Color createColor(Color color);
    Color getColorById(Long id);
}
