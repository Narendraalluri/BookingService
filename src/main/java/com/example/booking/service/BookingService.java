package com.example.booking.service;

import com.example.booking.entities.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingService {
    List<Booking> getBookingsForPassenger(UUID passengerId);
    Optional<Booking> getBookingDetails(UUID bookingId);
}
