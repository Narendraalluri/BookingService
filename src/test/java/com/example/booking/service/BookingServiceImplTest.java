package com.example.booking.service;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Passenger;
import com.example.booking.repositories.BookingRepository;
import com.example.booking.repositories.PassengerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private Booking booking;

    @Mock
    private Passenger passenger;

    @Test
    public void testGetBookingsForPassenger() {

        //given
        UUID passengerId = UUID.randomUUID();
        Mockito.when(passengerRepository.findById(booking.getPassengers().get(0).getId())).thenReturn(Optional.of(passenger));
        Mockito.when(bookingRepository.findAllByPassengersIn(passenger)).thenReturn(Collections.singletonList(booking));

        //when
        List<Booking> bookingList = bookingService.getBookingsForPassenger(passengerId);

        //then
        assertFalse(bookingList.isEmpty());
        assertEquals(booking, bookingList.get(0));

    }

    @Test
    public void testGetBookingDetails() {

        //given
        UUID bookingId = UUID.randomUUID();
        Mockito.when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        //when
        Optional<Booking> bookingOptional = bookingService.getBookingDetails(bookingId);

        //then
        assertTrue(bookingOptional.isPresent());
        assertEquals(booking, bookingOptional.get());

    }
}
