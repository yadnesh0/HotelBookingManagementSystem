package com.capg.hotelbookingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.Transaction;
import com.capg.hotelbookingmanagementsystem.repository.TransactionRepository;
import com.capg.hotelbookingmanagementsystem.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
     @Autowired
	private TransactionRepository transactionsrepository;
	
     @Override
	public Transaction addTransactions(Transaction transactions) {
    	 return transactionsrepository.saveAndFlush(transactions);	
	}
   
	
	
}
