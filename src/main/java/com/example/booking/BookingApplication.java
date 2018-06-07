package com.example.booking;

import com.example.booking.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingApplication {


	@Autowired
	PassengerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

}
