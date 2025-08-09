package com.sna.project.phonephop.PhoneShop.service.impl;

import com.sna.project.phonephop.PhoneShop.exception.ApiException;
import com.sna.project.phonephop.PhoneShop.model.dto.ProductSoldDTO;
import com.sna.project.phonephop.PhoneShop.model.dto.SaleDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Product;
import com.sna.project.phonephop.PhoneShop.model.entity.Sale;
import com.sna.project.phonephop.PhoneShop.model.entity.SaleDetail;
import com.sna.project.phonephop.PhoneShop.repository.ProductRepository;
import com.sna.project.phonephop.PhoneShop.repository.SaleDatailRepository;
import com.sna.project.phonephop.PhoneShop.repository.SaleRepository;
import com.sna.project.phonephop.PhoneShop.service.ProductService;
import com.sna.project.phonephop.PhoneShop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImple implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDatailRepository saleDatailRepository;
    @Override
    public void sell(SaleDTO saleDTO) {
        List<Long> productIds= saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();
        //validate product
        productIds.forEach(productService::getById);
        List<Product> products= productRepository.findAllById(productIds);
        Map<Long,Product> productMap= products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
        //validate stock

        saleDTO.getProducts()
                .forEach(ps->{
                    Product product= productMap.get(ps.getProductId());
                    if(product.getAvailableUnit()<ps.getNumberOfUnit()){
                        throw new ApiException(HttpStatus.BAD_REQUEST,"NOt enough product in stock ");
                    }
                });
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);
        // sale detail
        saleDTO.getProducts().forEach(ps->{
            Product product = productMap.get(ps.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetail.setSale(sale); // ✅ Set the relation to Sale

            saleDatailRepository.save(saleDetail);
           Integer availableUnit= product.getAvailableUnit()-ps.getNumberOfUnit();
           product.setAvailableUnit(availableUnit);
           productRepository.save(product);
        });
    }
    private void saveSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);
        // Sale Detail
        SaleDetail saleDetail = new SaleDetail();


    }
    private void validation(SaleDTO saleDTO) {
        saleDTO.getProducts().forEach(ps -> {
           Product product= productService.getById(ps.getProductId());
           if(product.getAvailableUnit()<ps.getNumberOfUnit()){
               throw new ApiException(HttpStatus.BAD_REQUEST,"Product enough in stock ");
           }

        });

    }

    private void validation2(SaleDTO saleDTO) {
        List<Long> productIds= saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();
        //validate product
        productIds.forEach(productService::getById);
        List<Product> products= productRepository.findAllById(productIds);
        Map<Long,Product> productMap= products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));


//
//        saleDTO.getProducts().stream()
//                .map(ProductSoldDTO::getProductId)
//                .forEach(productService::getById);
        //or
//                .forEach(productId -> {
//                    productService.getById(productId);
//                });


        //validate stock

        saleDTO.getProducts()
                .forEach(ps->{
                    Product product= productMap.get(ps.getProductId());
                    if(product.getAvailableUnit()<ps.getNumberOfUnit()){
                        throw new ApiException(HttpStatus.BAD_REQUEST,"NOt enough product in stock ");
                    }
                });

    }
}
