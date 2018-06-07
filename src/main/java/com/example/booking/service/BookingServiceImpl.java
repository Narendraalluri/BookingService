package com.example.booking.service;

import com.example.booking.entities.Booking;
import com.example.booking.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {


    private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public List<Booking> getBookingsForPassenger(UUID passengerId) {
        return bookingRepository.findAllByPassengerId(passengerId);
    }

    @Override
    public Optional<Booking> getBookingDetails(UUID bookingId) {
        return bookingRepository.findById(bookingId);
    }


}
