package com.example.demo.dto.Order;

import lombok.Data;

@Data
public class StripePaymentDto {
    private Long amount;
    private String currency;
}
