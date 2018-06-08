package com.example.booking.developertests;

import com.example.booking.model.Booking;
import com.example.booking.model.BookingDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class BookingClient {

    private static String BASE_URL = "http://localhost:8080/bookings";


    @Test
    public void testAPI() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceURL = String.format("%s?%s", BASE_URL, "uid=a57eace4-38bf-4cd9-93d5-936bd584c929");
        ResponseEntity<List<Booking>> bookingsForPassengerEntity =
                restTemplate.exchange(resourceURL,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
                        });
        List<Booking> bookingsForPassenger = bookingsForPassengerEntity.getBody();
        System.out.println(bookingsForPassenger.size());
        bookingsForPassenger.stream().forEach(booking -> {
            String bookingResourceURL = String.format("%s/%s", BASE_URL, booking.getBookingId());
            ResponseEntity<BookingDetails> bookingResponseEntity = restTemplate.exchange(bookingResourceURL,
                    HttpMethod.GET, null, BookingDetails.class);
            BookingDetails bookingDetails = bookingResponseEntity.getBody();
            System.out.println(bookingDetails.getBookingId());

            ResponseEntity<String> bookingResponseString = restTemplate.exchange(bookingResourceURL,
                    HttpMethod.GET, null, String.class);

            System.out.println(bookingResponseString.getBody());
        });
    }
}
