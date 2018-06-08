package com.example.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class BookingDetails  {
    @JsonProperty("id")
    private String bookingId;
    private Passenger passenger;
    private List<Flight> flights;
}
