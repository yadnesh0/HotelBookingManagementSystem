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

import com.capg.hotelbookingmanagementsystem.entity.Booking;
import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.entity.Room;
import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.exception.RecordNotFoundException;
import com.capg.hotelbookingmanagementsystem.exception.RoomNotAvailableException;
import com.capg.hotelbookingmanagementsystem.service.impl.BookingServiceImpl;



@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class BookingController {
	
	@Autowired
	private BookingServiceImpl bookingService;
		
	@PostMapping("/create")
	public ResponseEntity<Booking> addBooking(@RequestBody Booking bookingDetails){
		Booking bd = bookingDetails;
		try {
			bd = bookingService.addBooking(bookingDetails);
		} catch (RoomNotAvailableException e) {
			e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Booking>(bd,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Booking> updateBooking(@RequestBody Booking bookingDetails) throws RecordNotFoundException{
		Integer id = bookingDetails.getBookingId();
	    if(bookingService.findIfIdPresent(id)) {
	    	Booking bd = bookingService.addBooking(bookingDetails);
	    return new ResponseEntity<Booking>(bd,HttpStatus.OK);
	    }
	    else {
	    	throw new RecordNotFoundException("Booking Details with Id: "+id+" not found!!");
	    }
	}
	
	@DeleteMapping("/deleteById/{booking_id}")
	public ResponseEntity<String> deleteBooking(@PathVariable int booking_id) throws RecordNotFoundException{
	    if(bookingService.findIfIdPresent(booking_id)) {
	    	String bd = bookingService.removeBookingByBookingId(booking_id);
	    return new ResponseEntity<String>(bd,HttpStatus.OK);
	    }
	    else {
	    	throw new RecordNotFoundException("Booking Details with Id: "+booking_id+" not found!!");
	    }
	}
	
	@GetMapping("/getById/{booking_id}")
	public ResponseEntity<Booking> getBooking(@PathVariable int booking_id) throws RecordNotFoundException{
	    if(bookingService.findIfIdPresent(booking_id)) {
	    	Booking bd = bookingService.showBookingByBookingId(booking_id);
	    return new ResponseEntity<Booking>(bd,HttpStatus.OK);
	    }
	    else {
	    	throw new RecordNotFoundException("Booking Details with Id: "+booking_id+" not found!!");
	    }
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Booking>> getAllBookings(){
		List<Booking> bookings = bookingService.showAllBookings();
		return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
	}
	
	@GetMapping("/date") 
	 public ResponseEntity<List<Booking>> sortByBookingDate() {
		 return new ResponseEntity<List<Booking>>(bookingService.sortByDate(),HttpStatus.OK);
	 }
	
	@GetMapping("/rooms/{bookingId}")
	 public ResponseEntity<List<Room>> getRoomsByBookingId(@PathVariable int bookingId) throws RecordNotFoundException{
		 return new ResponseEntity<List<Room>>(bookingService.showRoomsByBooking(bookingId),HttpStatus.OK);
	 }
	
	@GetMapping("/user/{bookingId}")
	public ResponseEntity<User> getUserById(@PathVariable int bookingId) throws RecordNotFoundException{
		User user = bookingService.showUserByBooking(bookingId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{bookingId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable int bookingId) throws RecordNotFoundException{
		Hotel hotel = bookingService.showHotelByBooking(bookingId);
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	
	@GetMapping("/bookingsByUser/{userId}")
	public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable int userId) throws RecordNotFoundException{
		List<Booking> bookings = bookingService.bookingByUserId(userId);
		return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
	}
	
	
}