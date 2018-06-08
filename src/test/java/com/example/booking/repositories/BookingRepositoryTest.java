package com.example.booking.repositories;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Flight;
import com.example.booking.entities.Passenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void test_findAllByPassengerId() {
        //given
        String passengerFirstName = "firstName";
        String passengerLastName = "lastName";
        String passengerEmail = "email";

        String flightArrival = "arrival2";
        String flightDeparture = "departure2";
        String flightDepartureDate = "arrivalDate2";
        String flightArrivalDate = "departureDate2";

        Passenger passenger = new Passenger(passengerFirstName, passengerLastName, passengerEmail);
        passengerRepository.save(passenger);
        Flight flight = new Flight(flightDeparture, flightArrival,
                flightDepartureDate, flightArrivalDate);
        flightRepository.save(flight);
        Booking booking = entityManager.persistAndFlush(new Booking(passenger, Collections.singletonList(flight)));

        //when
        List<Booking> bookings = bookingRepository.findAllByPassengerId(booking.getPassenger().getId());

        //then
        assertFalse(bookings.isEmpty());
        assertEquals(booking.getPassenger().getId(), bookings.get(0).getPassenger().getId());
        assertEquals(passengerFirstName, bookings.get(0).getPassenger().getFirstName());
        assertEquals(passengerLastName, bookings.get(0).getPassenger().getLastName());
        assertEquals(passengerEmail, bookings.get(0).getPassenger().getEmail());
        assertFalse(bookings.get(0).getFlights().isEmpty());
        assertEquals(flightDeparture, bookings.get(0).getFlights().get(0).getDeparture());
        assertEquals(flightArrival, bookings.get(0).getFlights().get(0).getArrival());
        assertEquals(flightDepartureDate, bookings.get(0).getFlights().get(0).getDepartureDate());
        assertEquals(flightArrivalDate, bookings.get(0).getFlights().get(0).getArrivalDate());

    }

}
