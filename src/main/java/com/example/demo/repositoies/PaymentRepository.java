package com.example.demo.repositoies;

import com.example.demo.enity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Custom query methods can be defined here if needed
    // For example, find payments by user ID or status
}
