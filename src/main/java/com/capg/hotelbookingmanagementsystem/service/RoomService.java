package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;

import com.capg.hotelbookingmanagementsystem.entity.Room;

public interface RoomService {
	Room addRoom(Room roomDetails);
	Room updateRoom(Room roomDetails);
	String removeRoomById(int room_id);
	List<Room> showAllRoom();
	Room showRoomDetailsById(int room_id);
	
}
