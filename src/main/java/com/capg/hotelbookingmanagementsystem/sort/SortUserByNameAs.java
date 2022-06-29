package com.capg.hotelbookingmanagementsystem.sort;

import java.util.Comparator;

import com.capg.hotelbookingmanagementsystem.entity.User;

public class SortUserByNameAs implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		return o1.getUsername().compareToIgnoreCase(o2.getUsername());
	}
}