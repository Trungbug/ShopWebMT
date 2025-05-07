package com.example.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String thumbnailUrl;
    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;


}
