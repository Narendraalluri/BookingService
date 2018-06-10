package com.example.booking.developertests;

import com.example.booking.model.Booking;
import com.example.booking.model.BookingDetails;
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
        String resourceURL = String.format("%s?uid=%s", BASE_URL, "ebd839f8-aa0a-425e-8a75-fbd0823e260a");
        ResponseEntity<List<Booking>> bookingsForPassengerEntity =
                restTemplate.exchange(resourceURL,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
                        });
        List<Booking> bookingsForPassenger = bookingsForPassengerEntity.getBody();
        bookingsForPassenger.forEach(booking -> {
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
