package com.capg.hotelbookingmanagementsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="transaction_id")
	private Transaction transactions;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="booking_id")
	private Booking booking;
}
