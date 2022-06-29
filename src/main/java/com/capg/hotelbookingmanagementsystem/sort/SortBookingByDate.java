package com.capg.hotelbookingmanagementsystem.sort;

import java.util.Comparator;

import com.capg.hotelbookingmanagementsystem.entity.Booking;

public class SortBookingByDate implements Comparator<Booking>{

	@Override
	public int compare(Booking o1, Booking o2) {
		return o1.getBookedFrom().compareTo(o2.getBookedFrom());
	}

}
