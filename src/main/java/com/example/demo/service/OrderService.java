package com.example.demo.service;

import com.example.demo.dto.Order.OrderDTO;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    @Transactional
    OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
}
