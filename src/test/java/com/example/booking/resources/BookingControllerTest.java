package com.example.booking.resources;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Flight;
import com.example.booking.entities.Passenger;
import com.example.booking.model.BookingDetails;
import com.example.booking.repositories.BookingRepository;
import com.example.booking.repositories.FlightRepository;
import com.example.booking.repositories.PassengerRepository;
import com.example.booking.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @MockBean
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private Booking booking;

    @MockBean
    private Flight flight;

    @MockBean
    private Passenger passenger;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {
        given(bookingRepository.save(any())).willReturn(booking);
    }

    @Test
    public void test_getPassengerBookings() throws Exception{

        UUID passengerId = UUID.randomUUID();
        UUID bookingId = UUID.randomUUID();
        final String lastName = "lastName";
        final String departure = "departure";

        given(bookingService.getBookingsForPassenger(passengerId)).willReturn(Collections.singletonList(booking));
        given(booking.getId()).willReturn(bookingId);
        given(booking.getFlights()).willReturn(Collections.singletonList(flight));
        given(flight.getDeparture()).willReturn(departure);
        given(booking.getPassenger()).willReturn(passenger);
        given(passenger.getId()).willReturn(passengerId);
        given(passenger.getLastName()).willReturn(lastName);

        mvc.perform(get("/bookings?uid=" + passengerId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookingId", equalTo(bookingId.toString())))
                .andExpect(jsonPath("$[0].departure", equalTo(departure)))
                .andExpect(jsonPath("$[0].lastName", equalTo(lastName)));


    }

    @Test
    public void getBookingDetails() throws Exception{

        UUID bookingId = UUID.randomUUID();
        final String lastName = "lastName";
        final String firstName = "firstName";
        final String email = "email";
        final String arrival = "arrival";
        final String departure = "departure";
        final String departureDate = "departureDate";
        final String arrivalDate = "arrivalDate";

        BookingDetails bookingDetails = new BookingDetails();
        com.example.booking.model.Passenger passengerDTO = new com.example.booking.model.Passenger();
        com.example.booking.model.Flight flightDTO = new com.example.booking.model.Flight();

        bookingDetails.setPassenger(passengerDTO);
        bookingDetails.setFlights(Collections.singletonList(flightDTO));
        bookingDetails.setBookingId(bookingId.toString());

        passengerDTO.setEmail(email);
        passengerDTO.setFirstName(firstName);
        passengerDTO.setLastName(lastName);

        flightDTO.setArrival(arrival);
        flightDTO.setArrivalDate(arrivalDate);
        flightDTO.setDeparture(departure);
        flightDTO.setDepartureDate(departureDate);

        given(bookingService.getBookingDetails(bookingId)).willReturn(Optional.of(booking));
        given(modelMapper.map(booking, BookingDetails.class)).willReturn(bookingDetails);


        mvc.perform(get("/bookings/" + bookingId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(bookingId.toString())))
                .andExpect(jsonPath("$.passenger.firstName", equalTo(firstName)))
                .andExpect(jsonPath("$.passenger.lastName", equalTo(lastName)))
                .andExpect(jsonPath("$.passenger.email", equalTo(email)))
                .andExpect(jsonPath("$.flights", hasSize(1)))
                .andExpect(jsonPath("$.flights[0].arrival", equalTo(arrival)))
                .andExpect(jsonPath("$.flights[0].arrivalDate", equalTo(arrivalDate)))
                .andExpect(jsonPath("$.flights[0].departure", equalTo(departure)))
                .andExpect(jsonPath("$.flights[0].departureDate", equalTo(departureDate)));

    }
}
