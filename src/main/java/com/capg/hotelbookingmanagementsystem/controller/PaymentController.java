package com.capg.hotelbookingmanagementsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hotelbookingmanagementsystem.entity.Booking;
import com.capg.hotelbookingmanagementsystem.entity.Payment;
import com.capg.hotelbookingmanagementsystem.entity.Transaction;
import com.capg.hotelbookingmanagementsystem.service.impl.PaymentServiceImpl;


@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class PaymentController {
	
	@Autowired
	private PaymentServiceImpl paymentService;
   
	@PostMapping("/createPayment") 
	public ResponseEntity<Payment> cretaePayments(@RequestBody Payment payments){
		
		 Payment p = paymentService.addPayments(payments);
		return new ResponseEntity<Payment>(p,HttpStatus.CREATED);	
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Payment>> getAllPayments() {
		 return new ResponseEntity<List<Payment>>(paymentService.viewAllPayments(),HttpStatus.OK);
	 }
	
	@GetMapping("/transaction/{paymentId}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable int paymentId ) {
		 return new ResponseEntity<Transaction>(paymentService.getTransactionByPay(paymentId),HttpStatus.OK);
	 }
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable int userId) {
		List<Payment> payments =  paymentService.viewByUser(userId);
		 return new ResponseEntity<List<Payment>>(payments,HttpStatus.OK);
	 }
	
	@GetMapping("/booking/{paymentId}")
	public ResponseEntity<Booking> getBookingByPayment(@PathVariable int paymentId) {
		Booking booking =  paymentService.getBookingByPay(paymentId);
		 return new ResponseEntity<Booking>(booking,HttpStatus.OK);
	 }
}
