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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        return productRepository.findByModelIdAndColorId(modelId,colorId)
                .orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Product that have modelID: "+modelId+"   and colorId: "+colorId+" not found"));
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

    @Override
    public Map<Integer,String> uploadProduct(MultipartFile file) {
        Map<Integer,String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("product");
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();

            while(iterator.hasNext()){
                Integer rowNumber=0;
                     try {
                         Row row = iterator.next();
                         int celIndex = 0;
                         Cell cellNumber = row.getCell(celIndex++);
                         rowNumber = (int) cellNumber.getNumericCellValue();
                         Cell cellModelID = row.getCell(celIndex++);
                         Long modelID = (long) cellModelID.getNumericCellValue();

                         Cell cellColorId = row.getCell(celIndex++);
                         Long colorId = (long) cellColorId.getNumericCellValue();

                         Cell cellImportPrice = row.getCell(celIndex++);
                         double importPrice = cellImportPrice.getNumericCellValue();

                         Cell cellImportUnit = row.getCell(celIndex++);
                         double importUnit = cellImportUnit.getNumericCellValue();
                         if(importUnit<0){
                             throw new ApiException(HttpStatus.BAD_REQUEST,"Import unit must not be greather than zero");
                         }

                         Cell cellImportDate = row.getCell(celIndex++);
                         LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();
                         Product product = getByModelIdAndColorId(modelID, colorId);

                         Integer availableUnit = 0;
                         if (product.getAvailableUnit() != null) {
                             availableUnit = product.getAvailableUnit();
                         }
                         product.setAvailableUnit((int) (availableUnit + importUnit));
                         productRepository.save(product);

                         ProductImportHistory productImportHistory = new ProductImportHistory();
                         productImportHistory.setDateImport(importDate);
                         productImportHistory.setImportUnit((int) importUnit);
                         productImportHistory.setProduct(product);
                         productImportHistoryRepository.save(productImportHistory);

                         System.out.println("model ID : " + modelID + "   color ID : " + colorId + "   importPrice : " + importPrice + "   importUnit : " + importUnit);

                         // }catch (ApiException e){// ប្រសិនបើមានerror ផ្សេងពីនិងLike  formatte ចាប់អត់បានទេ  ចឹងគួរប្រើ Exception វិញ
                     }catch (Exception e){
                         map.put(rowNumber,e.getMessage());
                     }

            }


        } catch (IOException e) {
           e.printStackTrace();
        }
        return map;
    }
}
