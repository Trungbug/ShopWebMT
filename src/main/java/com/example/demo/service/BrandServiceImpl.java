package com.example.demo.service;

import com.example.demo.dto.product.BrandDTO;
import com.example.demo.dto.product.BrandResponse;
import com.example.demo.dto.product.CategoryDTO;
import com.example.demo.dto.product.CategoryResponse;
import com.example.demo.enity.Brand;
import com.example.demo.enity.Category;
import com.example.demo.exception.APIException;
import com.example.demo.repositoies.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    private FileService fileService;

    @Value("${project.image}") // đã có
    private String path;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BrandResponse getAllBrands(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Brand> brandPage = brandRepository.findAll(pageDetails);

        List<Brand> brands = brandPage.getContent();
        if (brands.isEmpty())
            throw new APIException("No brand created till now.");

        List<BrandDTO> brandDTOS = brands.stream()
                .map(brand -> modelMapper.map(brand, BrandDTO.class))
                .toList();

        BrandResponse brandResponse = new BrandResponse();
        brandResponse.setContent(brandDTOS);
        brandResponse.setPageNumber(brandPage.getNumber());
        brandResponse.setPageSize(brandPage.getSize());
        brandResponse.setTotalElements(brandPage.getTotalElements());
        brandResponse.setTotalPages(brandPage.getTotalPages());
        brandResponse.setLastPage(brandPage.isLast());
        return brandResponse;
    }

    @Override
    public BrandDTO creatBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        Brand brandFromDb = brandRepository.findByBrandName(brand.getBrandName());
        if (brandFromDb != null)
            throw new APIException("Brand already exists with this name.");
        Brand savedBrand = brandRepository.save(brand);
        return modelMapper.map(savedBrand, BrandDTO.class);
    }

    @Override
    public BrandDTO updateBrandImage(Long brandId, MultipartFile image) throws IOException {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new APIException("Brand not found with this id"));

        String fileName = fileService.uploadImage(path, image);
        brand.setULRImage(fileName);

        Brand savedBrand = brandRepository.save(brand);
        return modelMapper.map(savedBrand, BrandDTO.class);
    }

    @Override
    public BrandDTO deleteBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new APIException("Brand not found with this id"));
        brandRepository.delete(brand);
        return modelMapper.map(brand, BrandDTO.class);

    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO, Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new APIException("Brand not found with this id"));

        // Cập nhật dữ liệu từ DTO vào entity
        brand.setBrandName(brandDTO.getBrandName());
        brand.setDescription(brandDTO.getDescription());

        // Lưu lại entity đã được cập nhật
        Brand savedBrand = brandRepository.save(brand);
        return modelMapper.map(savedBrand, BrandDTO.class);
    }


}
