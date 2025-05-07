package com.example.demo.service;


import com.example.demo.dto.product.BrandDTO;
import com.example.demo.dto.product.BrandResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BrandService {
    BrandResponse getAllBrands(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    BrandDTO creatBrand(BrandDTO brandDTO);
    BrandDTO updateBrandImage(Long brandId, MultipartFile image) throws IOException;
    BrandDTO deleteBrand(Long brandId);

    BrandDTO updateBrand(BrandDTO brandDTO, Long brandId);
}
