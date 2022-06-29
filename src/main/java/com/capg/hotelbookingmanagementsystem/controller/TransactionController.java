package com.capg.hotelbookingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.capg.hotelbookingmanagementsystem.entity.Transaction;
import com.capg.hotelbookingmanagementsystem.service.impl.TransactionServiceImpl;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class TransactionController {
 
	 @Autowired
	private TransactionServiceImpl transactionsServiceImpl;
	 
	 @PostMapping("/createTransactions")
	 public ResponseEntity<Transaction> createTransactions(@RequestBody Transaction transactions)
	 {
		 Transaction t=transactionsServiceImpl.addTransactions(transactions);
		 return new ResponseEntity<Transaction>(t,HttpStatus.CREATED);
	 }
	 
	 
}
