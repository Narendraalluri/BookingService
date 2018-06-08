package com.example.booking.integration;

import com.example.booking.BookingApplication;
import com.example.booking.entities.Booking;
import com.example.booking.entities.Flight;
import com.example.booking.entities.Passenger;
import com.example.booking.repositories.BookingRepository;
import com.example.booking.repositories.FlightRepository;
import com.example.booking.repositories.PassengerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = BookingApplication.class)
@AutoConfigureMockMvc
public class BookingServiceIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    private Booking savedBooking;

    private static final String DEPARTURE = "departure";
    private static final String DEPARTURE_DATE = "departureDate";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String ARRIVAL = "arrival";

    @Before
    public void setup() {
        Flight flight = new Flight(DEPARTURE, ARRIVAL, DEPARTURE_DATE, ARRIVAL_DATE);
        flightRepository.save(flight);
        Passenger passenger = new Passenger(FIRST_NAME, LAST_NAME, EMAIL);
        passengerRepository.save(passenger);
        Booking booking = new Booking(passenger, Collections.singletonList(flight));
        savedBooking = bookingRepository.save(booking);
    }

    @Test
    public void test_getPassengerBookings() throws Exception{

        String passengerId = savedBooking.getPassenger().getId().toString();

        mvc.perform(get("/bookings?passengerId=" + passengerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookingId", equalTo(savedBooking.getId().toString())))
                .andExpect(jsonPath("$[0].departure", equalTo(DEPARTURE)))
                .andExpect(jsonPath("$[0].lastName", equalTo(LAST_NAME)));


    }

    @Test
    public void test_getBookingDetails() throws Exception{

        String bookingId = savedBooking.getId().toString();

        mvc.perform(get("/bookings/" + bookingId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(bookingId)))
                .andExpect(jsonPath("$.passenger.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.passenger.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.passenger.email", equalTo(EMAIL)))
                .andExpect(jsonPath("$.flights", hasSize(1)))
                .andExpect(jsonPath("$.flights[0].departure", equalTo(DEPARTURE)))
                .andExpect(jsonPath("$.flights[0].arrival", equalTo(ARRIVAL)))
                .andExpect(jsonPath("$.flights[0].departureDate", equalTo(DEPARTURE_DATE)))
                .andExpect(jsonPath("$.flights[0].arrivalDate", equalTo(ARRIVAL_DATE)));


    }

}
