package com.sna.project.phonephop.PhoneShop.repository;

import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {
   List<Brand> findByNameContaining(String name);
}
