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
import com.capg.hotelbookingmanagementsystem.service.impl.HotelServiceImpl;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class HotelController {
	
	@Autowired
	private HotelServiceImpl hotelService;
	
   
	@PostMapping("/create")
	public ResponseEntity<Hotel>createHotel(@RequestBody Hotel hotel){
		Hotel h= hotelService.addHotel(hotel);
		return new ResponseEntity<Hotel>(h,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Hotel>updateHotel(@RequestBody Hotel hotel) throws RecordNotFoundException{
		Integer id=hotel.getHotelId();
		if(hotelService.findIfIdPresent(id)) {
			return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel),HttpStatus.CREATED);
		}
		else {
			throw new RecordNotFoundException("Hotel with id"+id+"not found as per your request");
		}
		}
	
	@GetMapping("/list")
	public ResponseEntity<List<Hotel>> getAllHotels(){
		return new ResponseEntity<List<Hotel>>( hotelService.showAllHotels(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{hotelId}")
	public ResponseEntity<String> deleteHotelById(@PathVariable int hotelId) throws RecordNotFoundException{
		if(hotelService.findIfIdPresent(hotelId)) {
			String hotel=hotelService.removeHotelById(hotelId);
			return new ResponseEntity<String>(hotel,HttpStatus.OK);
		}
		else {
			throw new RecordNotFoundException("Hotel with id"+hotelId+"not found as per your request");
		}
	}
	
	@GetMapping("/getById/{hotelId}")
	public ResponseEntity<Hotel> gethotelById(@PathVariable int hotelId) throws RecordNotFoundException{
		if(hotelService.findIfIdPresent(hotelId)) {
			Hotel hotel=hotelService.showHotelById(hotelId);
			return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
		}
		else {
			throw new RecordNotFoundException("Hotel with id"+hotelId+"not found as per your request");
		}
	}
	
	@GetMapping("/priceAs") 
	 public ResponseEntity<List<Hotel>> sortByAmountAscending() {
		 return new ResponseEntity<List<Hotel>>(hotelService.sortByPriceAs(),HttpStatus.OK);
	 }
	
	@GetMapping("/nameAs") 
	 public ResponseEntity<List<Hotel>> sortByNameAscending() {
		 return new ResponseEntity<List<Hotel>>(hotelService.sortByNameAs(),HttpStatus.OK);
	 }
	
	@GetMapping("/filter/{city}") 
	 public ResponseEntity<List<Hotel>> FilterByCity(@PathVariable String city) {
		 return new ResponseEntity<List<Hotel>>(hotelService.filterByCity(city),HttpStatus.OK);
	 }
	
	
	@GetMapping("/roomByHotel/{hotelId}") 
	 public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable int hotelId) {
		 return new ResponseEntity<List<Room>>(hotelService.viewRoomsByHotel(hotelId),HttpStatus.OK);
	 }
}
