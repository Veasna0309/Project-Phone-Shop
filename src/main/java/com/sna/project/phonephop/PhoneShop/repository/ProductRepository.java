package com.sna.project.phonephop.PhoneShop.repository;

import com.sna.project.phonephop.PhoneShop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {
    Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);
}
