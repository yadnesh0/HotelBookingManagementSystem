package com.capg.hotelbookingmanagementsystem.sort;

import java.util.Comparator;

import com.capg.hotelbookingmanagementsystem.entity.Hotel;

public class SortHotelByPriceAs implements Comparator<Hotel> {

	@Override
	public int compare(Hotel o1, Hotel o2) {
		return (int) (o1.getAvgRatePerDay()-o2.getAvgRatePerDay());
	}

}
