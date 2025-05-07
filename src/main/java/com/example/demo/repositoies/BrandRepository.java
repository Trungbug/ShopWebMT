package com.example.demo.repositoies;

import com.example.demo.enity.Brand;
import com.example.demo.enity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByBrandName(String brandName);

}
