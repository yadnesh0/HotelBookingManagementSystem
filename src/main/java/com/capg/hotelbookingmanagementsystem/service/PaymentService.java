package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;

import com.capg.hotelbookingmanagementsystem.entity.Payment;

public interface PaymentService {
	
	Payment addPayments(Payment payments);
	List<Payment> viewAllPayments();
}
