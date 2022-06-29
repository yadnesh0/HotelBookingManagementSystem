package com.capg.hotelbookingmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.entity.Room;
import com.capg.hotelbookingmanagementsystem.exception.RecordNotFoundException;
import com.capg.hotelbookingmanagementsystem.service.impl.RoomServiceImpl;


@RestController
@RequestMapping("/room")
@CrossOrigin(  origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders ="*")
public class RoomController {
     
    @Autowired
	private RoomServiceImpl roomService;
	
	 @PostMapping("/create")
	 public ResponseEntity<Room> createRoomDetails(@RequestBody Room roomDetails)
	 {
		 Room rd = roomService.addRoom(roomDetails);
		 return new ResponseEntity<Room>(rd,HttpStatus.CREATED);
	 }
	 
	 @PutMapping("/update")
	 public ResponseEntity<Room> updateRoomDetails(@RequestBody Room roomDetails)throws RecordNotFoundException{
		 Integer id=roomDetails.getRoomId();
		 if(roomService.findIfIdPresent(id)){

			 return new ResponseEntity<Room>(roomService.updateRoom(roomDetails),HttpStatus.CREATED);
		 }
		 else {
			 throw new RecordNotFoundException("Rooms Details with Id : "+id+" not found ");
		 }
	 }
	 @DeleteMapping("/deletedById/{roomId}")
	 public ResponseEntity<String> deleteRooomDetailsById(@PathVariable int roomId) throws RecordNotFoundException{

		 if(roomService.findIfIdPresent(roomId)) {
			 String rd = roomService.removeRoomById(roomId);
			 return new ResponseEntity<String>(rd,HttpStatus.OK);
		 }
		 else {
			 throw new RecordNotFoundException("User with Id : "+roomId+" not found ");
		 }
	 }

	 @GetMapping("/list") 
	 public ResponseEntity<List<Room>> getAllRooms() {
		 return new ResponseEntity<List<Room>>(roomService.showAllRoom(),HttpStatus.OK);
	 }


	 @GetMapping("/getRoomById/{roomId}")
	 public ResponseEntity<Room> getRoomDetailsById(@PathVariable int roomId) throws RecordNotFoundException{

		 if(roomService.findIfIdPresent(roomId)) {
			 Room rd = roomService.showRoomDetailsById(roomId);
			 return new ResponseEntity<Room>(rd,HttpStatus.OK);
		 }
		 else {
			 throw new RecordNotFoundException("RoomDetails with Id : "+roomId+" not found ");
		 }
	 }
	 
	 @PutMapping("/setTrue/{roomId}")
	 public ResponseEntity<Room> setRoomTrue(@PathVariable int roomId) throws RecordNotFoundException{

		 if(roomService.findIfIdPresent(roomId)) {
			 Room rd = roomService.roomAvailabilityTrue(roomId);
			 return new ResponseEntity<Room>(rd,HttpStatus.OK);
		 }
		 else {
			 throw new RecordNotFoundException("RoomDetails with Id : "+roomId+" not found ");
		 }
	 }
	 
	 @PutMapping("/setFalse/{roomId}")
	 public ResponseEntity<Room> setRoomFalse(@PathVariable int roomId) throws RecordNotFoundException{

		 if(roomService.findIfIdPresent(roomId)) {
			 Room rd = roomService.roomAvailabilityFalse(roomId);
			 return new ResponseEntity<Room>(rd,HttpStatus.OK);
		 }
		 else {
			 throw new RecordNotFoundException("RoomDetails with Id : "+roomId+" not found ");
		 }
	 }
	 
	 @GetMapping("/priceAs/{hotelId}") 
	 public ResponseEntity<List<Room>> sortByAmountAscending(@PathVariable int hotelId) {
		 return new ResponseEntity<List<Room>>(roomService.sortByPriceA(hotelId),HttpStatus.OK);
	 }
	 
	 @GetMapping("/priceDs/{hotelId}") 
	 public ResponseEntity<List<Room>> sortByAmountDescending(@PathVariable int hotelId) {
		 return new ResponseEntity<List<Room>>(roomService.sortByPriceD(hotelId),HttpStatus.OK);
	 }
	 
	 @GetMapping("/roomsByHotel/{hotelId}")
	 public ResponseEntity<List<Room>> getRoomsByHotelId(@PathVariable int hotelId) throws RecordNotFoundException{

		 return new ResponseEntity<List<Room>>(roomService.showRoomsByHotel(hotelId),HttpStatus.OK);
	 }

	 @GetMapping("/hotel/{roomId}")
	 public ResponseEntity<Hotel> getHotelByRoom(@PathVariable int roomId){
  		 return new ResponseEntity<Hotel>(roomService.viewHotelByRoom(roomId),HttpStatus.OK);
	 }
	 
}
	 

