package com.sna.project.phonephop.PhoneShop.repository;

import com.sna.project.phonephop.PhoneShop.model.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}
