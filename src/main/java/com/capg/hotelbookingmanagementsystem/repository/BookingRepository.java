package com.capg.hotelbookingmanagementsystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capg.hotelbookingmanagementsystem.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
      public List<Booking> findByBookedFrom(Date date);
      public Booking findByBookingId(int id);
}
