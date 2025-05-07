package com.example.demo.controller;

import com.example.demo.constants.AppConstants;
import com.example.demo.dto.product.BrandDTO;
import com.example.demo.dto.product.BrandResponse;
import com.example.demo.dto.product.CategoryDTO;
import com.example.demo.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping("/admin/brands")
    public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody BrandDTO brandDTO){
        BrandDTO createdBrand = brandService.creatBrand(brandDTO);
        return new ResponseEntity<>(createdBrand, HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/brands/{brandId}")
    public ResponseEntity<BrandDTO> deleteBrand(@PathVariable Long brandId){
        BrandDTO deletedBrand = brandService.deleteBrand(brandId);
        return new ResponseEntity<>(deletedBrand, HttpStatus.OK);
    }


    @PutMapping("/admin/brands/{brandId}")
    public ResponseEntity<BrandDTO> updateBrand(@Valid @RequestBody BrandDTO brandDTO,
                                                      @PathVariable Long brandId){
        BrandDTO savedBrandDTO = brandService.updateBrand(brandDTO, brandId);
        return new ResponseEntity<>(savedBrandDTO, HttpStatus.OK);
    }
    @GetMapping("/public/brands")
    public ResponseEntity<BrandResponse> getAllBrands(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BRAND_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        BrandResponse brandResponse = brandService.getAllBrands(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(brandResponse, HttpStatus.OK);
    }
    @PutMapping("/admin/brands/{brandId}/image")
    public ResponseEntity<BrandDTO> updateBrandImage(@PathVariable Long brandId,
                                                     @RequestParam("images") MultipartFile image) throws IOException {
        BrandDTO updatedBrand = brandService.updateBrandImage(brandId, image);
        return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
    }
}
