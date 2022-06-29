package com.capg.hotelbookingmanagementsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private int hotelId; 
	
	@NotBlank(message = "City is mandatory")
	private String city;
	
	@Column(updatable = false)
	private String hotelName;
	
	private String address;
	private String description;
	private double avgRatePerDay;
	private String email;
	private String phone1;
	private String phone2;
	private String website;
		
}

