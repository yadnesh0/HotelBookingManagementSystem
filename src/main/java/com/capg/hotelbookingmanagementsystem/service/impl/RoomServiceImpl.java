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
import com.capg.hotelbookingmanagementsystem.service.RoomService;
import com.capg.hotelbookingmanagementsystem.sort.SortRoomByPriceAs;
import com.capg.hotelbookingmanagementsystem.sort.SortRoomByPriceDs;

import one.util.streamex.StreamEx;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
    private RoomRepository roomRepository;
	@Autowired
    private HotelRepository hotelRepository;
	
	@Override
	public Room addRoom(Room room) {
		Room r = room;
		Integer id = room.getHotel().getHotelId(); 
		Hotel h = hotelRepository.findById(id).get();
		if(h!= null) {
			r.setHotel(h);
			Room r1 = roomRepository.saveAndFlush(r);
			return r1 ;
		}
		return roomRepository.saveAndFlush(room);
	}

	@Override
	public Room updateRoom(Room room) {	
		Room r = room;
		Integer id = room.getHotel().getHotelId(); 
		Hotel h = hotelRepository.findById(id).get();
		if(h!= null) {
			r.setHotel(h);
			Room r1 = roomRepository.saveAndFlush(r);
			return r1 ;
		}
		return roomRepository.saveAndFlush(room);
	}

	@Override
	public String removeRoomById(int roomId) {
		roomRepository.deleteById(roomId);
		return "Room Details Deleted";
	}

	@Override
	public List<Room> showAllRoom() {
		List<Room> rooms = roomRepository.findAll();
	    return rooms;
	}

	@Override
	public Room showRoomDetailsById(int roomId) {
		Room room = roomRepository.findById(roomId).get();
		return room;
	}
	
	public Room roomAvailabilityTrue(int roomId) {
		Room r = roomRepository.findById(roomId).get();
		r.setAvailable(true);
		Room room =roomRepository.saveAndFlush(r);
		return room;
	}
	
	public Room roomAvailabilityFalse(int roomId) {
		Room r = roomRepository.findById(roomId).get();
		r.setAvailable(false);
		Room room =roomRepository.saveAndFlush(r);
		return room;
	}
	
	public boolean findIfIdPresent(int roomId) {
		if(roomRepository.findById(roomId).isPresent())
			return true;
	    return false;
	}
	
	public List<Room> sortByPriceA(int hotelId){
		List<Room> rooms = roomRepository.findAll();
		List<Room> roomsbyHotel = new ArrayList<Room>();
		List<Room> roomList = new ArrayList<Room>();
		
		roomsbyHotel = rooms.stream().filter(j -> j.getHotel().getHotelId()==hotelId).collect(Collectors.toList());
	
        
		List<Room> roomListFiltered = StreamEx.of(roomsbyHotel)
				  .distinct(Room::getRoomId)
				  .toList();
		
		roomListFiltered.forEach(i -> {
			Room r = i;
			Hotel h = i.getHotel();
			r.setHotel(h);
			roomList.add(r);
		});
		Collections.sort(roomList,new SortRoomByPriceAs());
		return roomList;
	}
	
	public List<Room> sortByPriceD(int hotelId){
		List<Room> rooms = roomRepository.findAll();
		List<Room> roomsbyHotel = new ArrayList<Room>();
		List<Room> roomList = new ArrayList<Room>();
		
		roomsbyHotel = rooms.stream().filter(j -> j.getHotel().getHotelId()==hotelId).collect(Collectors.toList());
	
        
		List<Room> roomListFiltered = StreamEx.of(roomsbyHotel)
				  .distinct(Room::getRoomId)
				  .toList();
		
		roomListFiltered.forEach(i -> {
			Room r = i;
			Hotel h = i.getHotel();
			r.setHotel(h);
			roomList.add(r);
		});
		Collections.sort(roomList,new SortRoomByPriceDs());
		return roomList;
	}
	
	public List<Room> showRoomsByHotel(int hotelId) {
		List<Room> room = roomRepository.findAll();
		List<Room> rooms = new ArrayList<Room>(); 
		
		room.forEach( i -> {
			if(i.getHotel().getHotelId() == hotelId) {
				rooms.add(i);
			}
		});	
	  return rooms;
	}
	
	public Hotel viewHotelByRoom(int roomId) {
		Room room = roomRepository.findById(roomId).get();
		Hotel h = room.getHotel();
		
		return h;
	}
	
}