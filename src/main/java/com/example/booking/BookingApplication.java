package com.example.booking;

import com.example.booking.entities.Booking;
import com.example.booking.entities.Flight;
import com.example.booking.entities.Passenger;
import com.example.booking.repositories.BookingRepository;
import com.example.booking.repositories.FlightRepository;
import com.example.booking.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class BookingApplication implements CommandLineRunner {

	private BookingRepository bookingRepository;
	private PassengerRepository passengerRepository;
	private FlightRepository flightRepository;

	@Autowired
	public BookingApplication(BookingRepository bookingRepository, PassengerRepository passengerRepository, FlightRepository flightRepository) {
		this.bookingRepository = bookingRepository;
		this.passengerRepository = passengerRepository;
		this.flightRepository = flightRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}


	@Override
	public void run(String... args) {
		Flight flight1 = new Flight("departure", "arrival", "departureDate", "arrivalDate");
		Flight flight2 = new Flight("departure2", "arrival2", "departureDate2", "arrivalDate2");
		flightRepository.save(flight1);
		flightRepository.save(flight2);
		Passenger passenger = new Passenger("firstName", "lastName", "email");
		passengerRepository.save(passenger);
		Booking booking = new Booking(passenger, Arrays.asList(flight1, flight2));
		Booking booking1 = new Booking(passenger, Collections.singletonList(flight2));
		Booking savedBooking = bookingRepository.save(booking);
		bookingRepository.save(booking1);
		System.out.println("Passenger Id " + (savedBooking != null ? savedBooking.getPassenger().getId() : ""));
	}
}
