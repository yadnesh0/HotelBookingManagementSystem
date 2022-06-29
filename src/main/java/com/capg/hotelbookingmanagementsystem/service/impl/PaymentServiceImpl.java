package com.capg.hotelbookingmanagementsystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.Booking;

import com.capg.hotelbookingmanagementsystem.entity.Payment;
import com.capg.hotelbookingmanagementsystem.entity.Transaction;
import com.capg.hotelbookingmanagementsystem.repository.BookingRepository;
import com.capg.hotelbookingmanagementsystem.repository.PaymentRepository;
import com.capg.hotelbookingmanagementsystem.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentsRepository;
	@Autowired
	private BookingRepository bookingDetailsRepository;
	@Override
	public Payment addPayments(Payment payments) {
		Payment pay = paymentsRepository.save(payments);
		return pay;
	}
	
	@Override
	public List<Payment> viewAllPayments() {
		List<Payment> payments = paymentsRepository.findAll();
		return payments;
	}
	
	public Transaction getTransactionByPay(int paymentId) {
	 Payment payment = paymentsRepository.findById(paymentId).get();
	 Transaction transaction = payment.getTransactions();
	 return transaction;
	}
		
	public List<Payment> viewByUser(int userId) {
		List<Booking> allbookings = bookingDetailsRepository.findAll();
		List<Payment> payments = paymentsRepository.findAll();
		List<Payment> list = new ArrayList<Payment>();

		List<Booking> bookings = allbookings.stream().filter(i -> i.getUsers().getUserId()==userId).toList();
	    for(Booking m : bookings) {
	    	for(Payment n : payments) {
	    		if(n.getBooking().getBookingId()==m.getBookingId()) {
	    			list.add(n);
	    		}
	    	}
	    }
		return list;
	}
	
	public Booking getBookingByPay(int paymentId) {
		Payment payment = paymentsRepository.findById(paymentId).get();
	    Booking booking = payment.getBooking();
	    return booking;
	}
	
}