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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class BookingApplication implements CommandLineRunner {

	private BookingRepository bookingRepository;
	private PassengerRepository passengerRepository;
	private FlightRepository flightRepository;

	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);


	public static String BOOTSTRAP_PASSENGER_ID;

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
		Date currentDate = new Date();

		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		LocalDateTime departureDate1 = localDateTime.plusDays(1);
		LocalDateTime arrivalDate1 = departureDate1.plusDays(1);
		LocalDateTime departureDate2 = arrivalDate1.plusDays(1);
		LocalDateTime arrivalDate2 = departureDate2.plusDays(1);

		Flight flight1 = new Flight("HEL", "JFK", dateFormat.format(departureDate1), dateFormat.format(arrivalDate1));
		Flight flight2 = new Flight("JFK", "HEL", dateFormat.format(departureDate1), dateFormat.format(arrivalDate2));
		flightRepository.save(flight1);
		flightRepository.save(flight2);
		Passenger passenger = new Passenger("Narendra", "Alluri", "narendravarmaalluri@gmail.com");
		Passenger passenger1 = new Passenger("Amrutha", "Jampana", "amrutha.90990@gmail.com");
		passengerRepository.save(passenger);
		passengerRepository.save(passenger1);
		Booking booking = new Booking(Arrays.asList(passenger, passenger1), Arrays.asList(flight1, flight2));
		Booking booking1 = new Booking(Arrays.asList(passenger, passenger1), Collections.singletonList(flight2));
		Booking savedBooking = bookingRepository.save(booking);
		Booking savedBooking1 = bookingRepository.save(booking1);
		bookingRepository.save(booking1);
		if (savedBooking != null) {
			BOOTSTRAP_PASSENGER_ID = savedBooking.getPassengers().get(0).getId().toString();
			System.out.println("Passenger Id " + savedBooking.getPassengers().get(0).getId());
		}
	}
}
