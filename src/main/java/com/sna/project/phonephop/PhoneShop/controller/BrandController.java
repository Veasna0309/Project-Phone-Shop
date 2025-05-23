package com.sna.project.phonephop.PhoneShop.controller;

import com.sna.project.phonephop.PhoneShop.Mapper.BrandMapper;
import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.dto.PageDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import com.sna.project.phonephop.PhoneShop.model.response.ApiResponse;
import com.sna.project.phonephop.PhoneShop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BandCombineOp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/search/{id}")
    public ResponseEntity<ApiResponse<Brand>> getBrandById(@PathVariable Integer id){
        ApiResponse<Brand> response=ApiResponse.<Brand>builder()
                .message("id :"+id+" is found")
                .status(HttpStatus.ACCEPTED)
                .payload(brandService.findBrandById(id))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PostMapping("/save")
    public Brand createBrand(@RequestBody BrandDTO brand){
        return brandService.SaveBrand(brand);
    }
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllBrands(){
//        ApiResponse<List<BrandDTO>> response=ApiResponse.<List<BrandDTO>>builder()
//                .message("All brands found")
//                .status(HttpStatus.ACCEPTED)
//                .payload(brandService.findAllBrands().stream().map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand)).collect(Collectors.toList()))
//                .timestamp(LocalDateTime.now())
//                .build();
//       return new ResponseEntity<>(response, HttpStatus.OK);
//
//    }
     @PostMapping("/update/{id})")
    public Brand updateBrand(@PathVariable Integer id, @RequestBody BrandDTO brand){
        return brandService.UpdateBrandById(id, brand);
     }
     @DeleteMapping("delete/{id}")
    public String deleteBrand(@PathVariable Integer id){
        return brandService.DeleteBrandById(id);
     }
     @GetMapping("/filter")
    public ResponseEntity<?> findByName(@RequestParam("name") String name){
        ApiResponse<List<BrandDTO>> response=ApiResponse.<List<BrandDTO>>builder()
                .message("Brands found")
                .status(HttpStatus.ACCEPTED)
                .payload(brandService.filterByName(name).stream().map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand)).collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
     }
     @GetMapping()
     public ResponseEntity<?> findAllBrandByUsingSpec(@RequestParam Map<String,String> param){
        ApiResponse<List<BrandDTO>> response=ApiResponse.<List<BrandDTO>>builder()
                .message("Brands found")
                .status(HttpStatus.ACCEPTED)
                .payload(brandService.findAllBrand(param).stream().map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand)).collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
     }
     @GetMapping("/pagination")
    public ResponseEntity<?> findAllByPagination(@RequestParam Map<String,String> param){
       Page<Brand> page= brandService.Pagination(param);
       PageDTO pageDTO=new PageDTO(page);


       return new ResponseEntity<>(pageDTO, HttpStatus.OK);
     }

}
