package com.example.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@ToString
public class Booking extends ResourceSupport {

    private String bookingId;
    private String lastName;
    private String departure;



}
