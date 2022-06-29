package com.capg.hotelbookingmanagementsystem.sort;

import java.util.Comparator;

import com.capg.hotelbookingmanagementsystem.entity.Room;

public class SortRoomByPriceAs implements Comparator<Room>{

	@Override
	public int compare(Room o1, Room o2) {
		return (int) (o1.getRatePerDay()-o2.getRatePerDay());
	}

}
