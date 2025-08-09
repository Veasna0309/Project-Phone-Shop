package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.Mapper.ProductMapper;
import com.sna.project.phonephop.PhoneShop.exception.ApiException;
import com.sna.project.phonephop.PhoneShop.exception.DuplicateProductException;
import com.sna.project.phonephop.PhoneShop.exception.ResourceNotFoundException;
import com.sna.project.phonephop.PhoneShop.model.dto.ProductDTO;
import com.sna.project.phonephop.PhoneShop.model.dto.ProductImportDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Product;
import com.sna.project.phonephop.PhoneShop.model.entity.ProductImportHistory;
import com.sna.project.phonephop.PhoneShop.repository.ProductImportHistoryRepository;
import com.sna.project.phonephop.PhoneShop.repository.ProductRepository;
import com.sna.project.phonephop.PhoneShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductImportHistoryRepository productImportHistoryRepository;


    @Override
    public Product createProduct(ProductDTO productDTO) {
      boolean exist =productRepository.findByModelIdAndColorId(productDTO.getModelId(), productDTO.getColorId()).isPresent();
      if(exist){
          throw new DuplicateProductException("Duplicate product found");
      }
       Product product=productMapper.toProduct(productDTO);
       String name=product.getModel().getName();
       product.setName(name);
        return productRepository.save(product);
    }
    @Override
    public Product getById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product",id));
    }
    @Override
    public void importProduct(ProductImportDTO productImportDTO) {
        if(productImportDTO.getImportUnit()==null){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Import unit must not be null");
        }
        //update available product unit
       Product product= getById(productImportDTO.getProductId());
       Integer availableUnit=0;
       if(product.getAvailableUnit()!=null){
           availableUnit=product.getAvailableUnit();
       }
       product.setAvailableUnit(availableUnit+productImportDTO.getImportUnit());
       productRepository.save(product);
       //save product import history
       ProductImportHistory importHistory= productMapper.toProductImportHistory(productImportDTO,product);
       productImportHistoryRepository.save(importHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal salePrice) {
        Product product = getById(productId);
        product.setSalePrice(salePrice);
        productRepository.save(product);
    }

    @Override
    public void validateStock(Long productId, Integer numberOfUnit) {

    }
}
