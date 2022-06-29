package com.capg.hotelbookingmanagementsystem.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.entity.Room;
import com.capg.hotelbookingmanagementsystem.repository.HotelRepository;
import com.capg.hotelbookingmanagementsystem.repository.RoomRepository;
import com.capg.hotelbookingmanagementsystem.service.HotelService;
import com.capg.hotelbookingmanagementsystem.sort.SortHotelByNameAs;
import com.capg.hotelbookingmanagementsystem.sort.SortHotelByPriceAs;

import one.util.streamex.StreamEx;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Hotel addHotel(Hotel hotel) {
		return hotelRepository.saveAndFlush(hotel);
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		return hotelRepository.saveAndFlush(hotel);
	}

	@Override
	public String removeHotelById(int hotelId) { 
		hotelRepository.deleteById(hotelId);
		 return "Hotel deleted";
	}

	@Override
	public List<Hotel> showAllHotels() {
		 List<Hotel> hotels = hotelRepository.findAll(); 
		 return hotels;
		 	
	}

	@Override
	public Hotel showHotelById(int hotelId) {
		Hotel h = hotelRepository.findById(hotelId).get();
		return h;
	}
	
	public boolean findIfIdPresent(int hotelId) {
		if(hotelRepository.findById(hotelId).isPresent())
			return true;
	    return false;
	}
	
	public List<Hotel> sortByPriceAs(){
		List<Hotel> hotels = hotelRepository.findAll();
		List<Hotel> hotelList = hotels;
		 Collections.sort(hotelList,new SortHotelByPriceAs());
		 return hotelList;
	}
	
	public List<Hotel> sortByNameAs(){
		List<Hotel> hotels = hotelRepository.findAll();
		List<Hotel> hotelList = hotels;
		 Collections.sort(hotelList,new SortHotelByNameAs());
		 return hotelList;
	}
	
	public List<Hotel> filterByCity(String city){
		List<Hotel> hotel = hotelRepository.findAll();
		List<Hotel> hotelList = hotel;
		 return hotelList;
	}
	
	public List<Room> viewRoomsByHotel(int hotelId){
		List<Room> rooms = roomRepository.findAll();
		List<Room> roomsbyHotel = new ArrayList<Room>();
		
		roomsbyHotel = rooms.stream().filter(j -> j.getHotel().getHotelId()==hotelId).collect(Collectors.toList());
		
		List<Room> roomListFiltered = StreamEx.of(roomsbyHotel)
				  .distinct(Room::getRoomId)
				  .toList();
		
	
		return roomListFiltered;
	}

}