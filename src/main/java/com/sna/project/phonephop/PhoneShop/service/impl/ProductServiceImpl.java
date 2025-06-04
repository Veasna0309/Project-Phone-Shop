package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.Mapper.ProductMapper;
import com.sna.project.phonephop.PhoneShop.exception.DuplicateProductException;
import com.sna.project.phonephop.PhoneShop.exception.ResourceNotFoundException;
import com.sna.project.phonephop.PhoneShop.model.dto.ProductDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Product;
import com.sna.project.phonephop.PhoneShop.repository.ProductRepository;
import com.sna.project.phonephop.PhoneShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


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
}
