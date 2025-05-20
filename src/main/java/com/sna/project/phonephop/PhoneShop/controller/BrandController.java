package com.sna.project.phonephop.PhoneShop.controller;

import com.sna.project.phonephop.PhoneShop.model.dto.BrandDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Brand;
import com.sna.project.phonephop.PhoneShop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/search/{id}")
    public Brand getBrandById(@PathVariable Integer id){
        return brandService.findBrandById(id);
    }
    @PostMapping("/save")
    public Brand createBrand(@RequestBody BrandDTO brand){
        return brandService.SaveBrand(brand);
    }
    @GetMapping("/getAll")
    public List<Brand> getAllBrands(){
        return brandService.findAllBrands();
    }
     @PostMapping("/update/{id})")
    public Brand updateBrand(@PathVariable Integer id, @RequestBody BrandDTO brand){
        return brandService.UpdateBrandById(id, brand);
     }
     @DeleteMapping("delete/{id}")
    public String deleteBrand(@PathVariable Integer id){
        return brandService.DeleteBrandById(id);
     }
}
