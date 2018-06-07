package com.example.booking.repositories;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, String> {

}
