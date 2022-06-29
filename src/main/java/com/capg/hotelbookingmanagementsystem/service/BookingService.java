package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;
import com.capg.hotelbookingmanagementsystem.entity.Booking;
import com.capg.hotelbookingmanagementsystem.exception.RoomNotAvailableException;

public interface BookingService {
	Booking addBooking(Booking bookingDetails) throws RoomNotAvailableException;
	Booking updateBooking(Booking bookingDetails);
	String removeBookingByBookingId(int bookingID );
	List<Booking> showAllBookings();
	Booking showBookingByBookingId(int bookingID);
}
