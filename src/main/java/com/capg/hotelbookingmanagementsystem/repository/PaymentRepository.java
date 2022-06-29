package com.capg.hotelbookingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.hotelbookingmanagementsystem.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
 
}
