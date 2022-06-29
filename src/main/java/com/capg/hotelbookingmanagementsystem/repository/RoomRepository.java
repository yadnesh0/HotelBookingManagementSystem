package com.capg.hotelbookingmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.hotelbookingmanagementsystem.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	public List<Room> findByIsAvailableTrue();
	public List<Room> findByHotelHotelIdAndIsAvailableTrue(int hotel_id);

}
