package com.capg.hotelbookingmanagementsystem.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booking_id")
	private int bookingId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date bookedFrom;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date bookedTo;
	private int noOfAdults;
	private int noOfChildren;
	private double amount;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="user_id")
    private User users;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="hotel_id")
	private Hotel hotel;

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_rooms",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "room_id"))
	private List<Room> rooms = new ArrayList<>();
	
}