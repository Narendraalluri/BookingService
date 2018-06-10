package com.example.booking.service;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Passenger;
import com.example.booking.repositories.BookingRepository;
import com.example.booking.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {


    private BookingRepository bookingRepository;

    private PassengerRepository passengerRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, PassengerRepository passengerRepository) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
    }


    @Override
    public List<Booking> getBookingsForPassenger(UUID passengerId) {
        Optional<Passenger> passenger = passengerRepository.findById(passengerId);
        if (passenger.isPresent()) {
            return bookingRepository.findAllByPassengersIn(passenger.get());
        }
        throw new RuntimeException("Passenger Id not found");
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingDetails(UUID bookingId) {
        return bookingRepository.findById(bookingId);
    }


}
