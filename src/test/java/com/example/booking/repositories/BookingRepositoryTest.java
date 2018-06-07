package com.example.booking.repositories;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Flight;
import com.example.booking.entities.Passenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void save() {
//
//        List<Passenger> passengers = passengerRepository.findAll();
//        Passenger passenger = Passenger.builder()
//                .email("email")
//                .firstName("firstName")
//                .lastName("lastName")
//                .build();
//
//        Passenger savedPassenger = passengerRepository.save(passenger);
//        passengers = passengerRepository.findAll();
//
//        Flight flight = Flight.builder()
//                .arrival("arrival")
//                .departure("departure")
//                .arrivalDate("arrivalDate")
//                .departureDate("departureDate")
//                .build();
//        Flight savedFlight = flightRepository.save(flight);
//        List<Flight> flights = flightRepository.findAll();
//
//
//        Booking booking = Booking.builder()
//                .flights(flights)
//                .passenger(passenger)
//                .build();
//        Booking savedBooking = bookingRepository.save(booking);
//        List<Booking> bookings = bookingRepository.findAll();
//        System.out.println(bookings);
    }

}
