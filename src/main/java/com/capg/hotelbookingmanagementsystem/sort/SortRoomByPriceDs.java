package com.capg.hotelbookingmanagementsystem.sort;

import java.util.Comparator;

import com.capg.hotelbookingmanagementsystem.entity.Room;

public class SortRoomByPriceDs implements Comparator<Room>{

	@Override
	public int compare(Room o1, Room o2) {
		return (int) (o2.getRatePerDay()-o1.getRatePerDay());
	}

}

