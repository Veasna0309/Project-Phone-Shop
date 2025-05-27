package com.sna.project.phonephop.PhoneShop.repository;

import com.sna.project.phonephop.PhoneShop.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model> {
    @Query("SELECT m FROM Model m WHERE m.brand.id = :brandId")
    List<Model> findModelsByBrandId(@Param("brandId") Long brandId);
}
