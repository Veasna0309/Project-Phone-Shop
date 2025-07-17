package com.sna.project.phonephop.PhoneShop.repository;

import com.sna.project.phonephop.PhoneShop.model.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long> {
}
