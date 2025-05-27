package com.sna.project.phonephop.PhoneShop.controller;

import com.sna.project.phonephop.PhoneShop.Mapper.ModelMapper;
import com.sna.project.phonephop.PhoneShop.model.dto.ModelDTO;
import com.sna.project.phonephop.PhoneShop.model.dto.PageDTO;
import com.sna.project.phonephop.PhoneShop.model.entity.Model;
import com.sna.project.phonephop.PhoneShop.model.response.ApiResponse;
import com.sna.project.phonephop.PhoneShop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;
    private final ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity save(@RequestBody ModelDTO model) {
        /*
    //    Model models=ModelMapper.INSTANCE.toModel(model);  ប្រើវាអត់កើតទេ ព្រោះ autowire brandservice អត់កើត
//        Model models=modelMapper.toModel(model);
       ប្រើនៅនេះបាន លុះត្រា តែ

*/
        ModelDTO modeldto = modelMapper.toModelDTO(modelService.save(model));
        ApiResponse<ModelDTO> response = ApiResponse.<ModelDTO>builder()
                .message("Success")
                .status(HttpStatus.ACCEPTED)
                .payload(modeldto)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllModels() {
        ApiResponse<List<Model>> response = ApiResponse.<List<Model>>builder()
                .message("Success")
                .status(HttpStatus.ACCEPTED)
                .payload(modelService.getAllModels())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModelById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<ModelDTO>builder().message("Success")
                        .message("Success")
                        .status(HttpStatus.ACCEPTED)
                        .payload(modelMapper.toModelDTO(modelService.findModelById(id)))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> updateModel(@PathVariable Long id, @RequestBody ModelDTO model) {
        return ResponseEntity.ok(ApiResponse.<ModelDTO>builder()
                .message("Success")
                .status(HttpStatus.ACCEPTED)
                .payload(modelMapper.toModelDTO(modelService.updaeModelById(id, model)))
                .timestamp(LocalDateTime.now())
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable("id") Long id) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("Success")
                .status(HttpStatus.ACCEPTED)
                .payload(modelService.deleteModelById(id))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<?> getModelByBrandId(@PathVariable("brandId") Long brandId) {
        ApiResponse<List<Model>> response = ApiResponse.<List<Model>>builder()
                .message("success")
                .status(HttpStatus.ACCEPTED)
                .payload(modelService.findModelByBrandId(brandId))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllByIdAndName(@RequestParam Map<String,String> params) {
        return ResponseEntity.ok(ApiResponse.<List<Model>>builder()
                .message("Success")
                .status(HttpStatus.ACCEPTED)
                .payload(modelService.findModelByIdAndName(params))
                .timestamp(LocalDateTime.now())
                .build());
    };
    @GetMapping("/Pagination")
    public ResponseEntity<?> getModelPagination(@RequestParam Map<String,String> param) {
       Page<Model> page= modelService.PaginationModel(param);

        return ResponseEntity.ok(ApiResponse.<PageDTO>builder()
        .message("Success")
                .status(HttpStatus.ACCEPTED)
                .payload(new PageDTO(page))
                .timestamp(LocalDateTime.now())
                .build());
    }

}