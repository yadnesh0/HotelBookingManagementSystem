package com.capg.hotelbookingmanagementsystem.sort;

import java.util.Comparator;

import com.capg.hotelbookingmanagementsystem.entity.Hotel;

public class SortHotelByNameAs implements Comparator<Hotel> {

	@Override
	public int compare(Hotel o1, Hotel o2) {
		return o1.getHotelName().compareToIgnoreCase(o2.getHotelName());
	}

}
