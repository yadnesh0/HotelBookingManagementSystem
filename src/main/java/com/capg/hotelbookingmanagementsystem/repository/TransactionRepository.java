package com.capg.hotelbookingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.hotelbookingmanagementsystem.entity.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
