package com.example.booking.repositories;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAllByPassengersIn(Passenger passenger);
}

