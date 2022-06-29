package com.capg.hotelbookingmanagementsystem.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.hotelbookingmanagementsystem.entity.Booking;
import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.entity.Payment;
import com.capg.hotelbookingmanagementsystem.entity.Room;
import com.capg.hotelbookingmanagementsystem.entity.Transaction;
import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.exception.RoomNotAvailableException;
import com.capg.hotelbookingmanagementsystem.repository.BookingRepository;
import com.capg.hotelbookingmanagementsystem.repository.HotelRepository;
import com.capg.hotelbookingmanagementsystem.repository.PaymentRepository;
import com.capg.hotelbookingmanagementsystem.repository.RoomRepository;
import com.capg.hotelbookingmanagementsystem.repository.UserRepository;
import com.capg.hotelbookingmanagementsystem.service.BookingService;
import com.capg.hotelbookingmanagementsystem.sort.SortBookingByDate;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Booking addBooking(Booking bookingDetails) throws RoomNotAvailableException{
		Booking booking = bookingDetails;
		long daysDiff = 0l;	

		List<Booking> bookingList = bookingRepository.findAll();	

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String reqfrom= formatter.format(bookingDetails.getBookedFrom());
		String reqTo= formatter.format(bookingDetails.getBookedTo());

		List<Room> reqRooms = bookingDetails.getRooms(); 
		List<Integer> list = new ArrayList<Integer>();
		reqRooms.forEach(a -> list.add(a.getRoomId()));
		List<Room> rooms = new ArrayList<Room>(); 
		
		try {

			Date  dateBefore = formatter.parse(reqfrom);
			Date  dateAfter = formatter.parse(reqTo);
			// Calculate the number of days between dates
			long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
			daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
			System.out.println("The number of days between dates: " + daysDiff);

			/*..........................For Rooms.............................*/
			
			bookingList.forEach( i -> {
				String from = formatter.format(i.getBookedFrom());
				String to = formatter.format(i.getBookedTo());
				Date dateStart = new Date();
				Date dateEnd = new Date();
				try {
					dateStart = formatter.parse(from);
					dateEnd = formatter.parse(to);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(i.getRooms().equals(reqRooms)) {
					if(dateStart.compareTo(dateBefore)==0 && dateEnd.compareTo(dateAfter)==0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}else if(dateStart.compareTo(dateAfter)==0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}else if(dateEnd.compareTo(dateBefore)==0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}else if(dateStart.compareTo(dateBefore)==0 && dateEnd.compareTo(dateAfter)>0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}else if(dateStart.compareTo(dateBefore)<0 && dateEnd.compareTo(dateAfter)==0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		list.forEach( i -> {
			Room r = roomRepository.findById(i).get();
			rooms.add(r);
		});
		booking.setRooms(rooms);
		/*..........................For Hotel.............................*/
		Hotel h = hotelRepository.findById(booking.getHotel().getHotelId()).get();
		booking.setHotel(h);
		/*..........................For User.............................*/
		User u = userRepository.findById(booking.getUsers().getUserId()).get();
		booking.setUsers(u);
		
		Booking b = bookingRepository.saveAndFlush(booking);
		/*..........................For Payment.............................*/
		Transaction trans = new Transaction();
		trans.setAmount(booking.getAmount());
		
		Payment payment = new Payment();
		payment.setTransactions(trans);
		payment.setBooking(b);
		paymentRepository.saveAndFlush(payment);
		return b;		
	}
	
	

	@Override
	@Transactional
	public Booking updateBooking(Booking bookingDetails) {
		Booking booking = bookingDetails;
		Integer hotelId = bookingDetails.getRooms().get(0).getHotel().getHotelId();
		long daysDiff = 0l;	

		List<Booking> bookingList = bookingRepository.findAll();	

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String reqfrom= formatter.format(bookingDetails.getBookedFrom());
		String reqTo= formatter.format(bookingDetails.getBookedTo());

		List<Room> reqRooms = bookingDetails.getRooms(); 
		List<Integer> list = new ArrayList<Integer>();
		reqRooms.forEach(a -> list.add(a.getRoomId()));
		List<Room> rooms = new ArrayList<Room>(); 
		
		try {

			Date  dateBefore = formatter.parse(reqfrom);
			Date  dateAfter = formatter.parse(reqTo);
			// Calculate the number of days between dates
			long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
			daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
			System.out.println("The number of days between dates: " + daysDiff);

			/*..........................For Rooms.............................*/
			
			bookingList.forEach( i -> {
				String from = formatter.format(i.getBookedFrom());
				String to = formatter.format(i.getBookedTo());
				Date dateStart = new Date();
				Date dateEnd = new Date();
				try {
					dateStart = formatter.parse(from);
					dateEnd = formatter.parse(to);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(i.getRooms().equals(reqRooms)) {
					if(dateStart.compareTo(dateBefore)==0 && dateEnd.compareTo(dateAfter)==0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}else if(dateStart.compareTo(dateAfter)==0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}else if(dateEnd.compareTo(dateBefore)==0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}else if(dateStart.compareTo(dateBefore)==0 && dateEnd.compareTo(dateAfter)>0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}else if(dateStart.compareTo(dateBefore)<0 && dateEnd.compareTo(dateAfter)==0) {
						throw new RoomNotAvailableException("Room Not Available on this day");
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		list.forEach( i -> {
			Room r = roomRepository.findById(i).get();
			rooms.add(r);
		});
		booking.setRooms(rooms);
		/*..........................For Hotel.............................*/
		Hotel h = hotelRepository.findById(hotelId).get();
		booking.setHotel(h);
		/*..........................For User.............................*/
		User u = userRepository.findById(booking.getUsers().getUserId()).get();
		booking.setUsers(u);
		
		Booking b = bookingRepository.saveAndFlush(booking);
		/*..........................For Payment.............................*/
		Transaction trans = new Transaction();
		trans.setAmount(booking.getAmount());
		
		Payment payment = new Payment();
		payment.setTransactions(trans);
		payment.setBooking(b);
	    paymentRepository.saveAndFlush(payment);
		return booking;		
	}

	@Override
	public String removeBookingByBookingId(int bookingID) {
		bookingRepository.deleteById(bookingID);
		return "Booking Deleted";
	}

	@Override
	public List<Booking> showAllBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		return bookings;
	}

	@Override
	public Booking showBookingByBookingId(int bookingID) {
		Booking booking = bookingRepository.findById(bookingID).get();
		return booking;
	}

	public boolean findIfIdPresent(int bookingId) {
		if (bookingRepository.findById(bookingId).isPresent())
			return true;
		return false;
	}

	public List<Booking> sortByDate() {
		List<Booking> bookings = bookingRepository.findAll();
		Collections.sort(bookings, new SortBookingByDate());
		return bookings;
	}

	public List<Room> showRoomsByBooking(int bookingId) {
		Booking booking = bookingRepository.findById(bookingId).get();

		List<Room> rooms = booking.getRooms();
		return rooms;
	}

	public User showUserByBooking(int bookingId) {
		Booking booking = bookingRepository.findById(bookingId).get();
		User u = booking.getUsers();
		return u;
	}

	public Hotel showHotelByBooking(int bookingId) {
		Booking booking = bookingRepository.findById(bookingId).get();
		Hotel h = booking.getHotel();
		return h;
	}
	
	
	public List<Booking> bookingByUserId(int userId) {
		List<Booking> allbookings = bookingRepository.findAll();
		List<Booking> bookings =  new ArrayList<Booking>();
        
		allbookings.forEach( j ->{
			if(j.getUsers().getUserId()==userId) {
			   bookings.add(j);
			}
		});
		Collections.sort(bookings, new SortBookingByDate());
		return bookings;
	}
	
	
}