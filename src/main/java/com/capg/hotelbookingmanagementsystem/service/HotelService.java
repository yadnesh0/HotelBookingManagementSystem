package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;

import com.capg.hotelbookingmanagementsystem.entity.Hotel;

public interface HotelService {
	
	Hotel addHotel(Hotel hotel);
	Hotel updateHotel(Hotel hotel);
	String removeHotelById(int hotel_id);
	List<Hotel> showAllHotels(); 
	Hotel showHotelById(int hotel_id);
}
