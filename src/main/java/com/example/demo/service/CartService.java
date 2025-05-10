package com.example.demo.service;

import com.example.demo.dto.Cart.CartDTO;
import com.example.demo.dto.Cart.CartItemDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
    List<CartDTO> getAllCarts();

    CartDTO getCart(String emailId, Long cartId);

    @Transactional
    CartDTO updateProductQuantityInCart(Long productId, Integer quantity);

    String deleteProductFromCart(Long cartId, Long productId);

    void updateProductInCarts(Long cartId, Long productId);

    String createOrUpdateCartWithItems(List<CartItemDTO> cartItems);
}
