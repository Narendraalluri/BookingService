package com.example.booking.repositories;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {

}
