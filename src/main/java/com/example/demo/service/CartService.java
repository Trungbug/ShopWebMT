package com.example.demo.service;

import com.example.demo.dto.Cart.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
}
