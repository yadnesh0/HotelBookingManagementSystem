package com.capg.hotelbookingmanagementsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
	private int roomId;
    @Column(unique = true, updatable = false)
	private String roomNo;
	private String roomType;
	private double ratePerDay;
	private boolean isAvailable;
		
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
}
	


