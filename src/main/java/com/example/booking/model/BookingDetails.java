package com.example.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Setter
@Getter
@ToString
public class BookingDetails extends ResourceSupport {
    @JsonProperty("id")
    private String bookingId;
    private Passenger passenger;
    private List<Flight> flights;
}
