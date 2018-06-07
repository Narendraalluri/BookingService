package com.example.booking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
class Flight {

    private String departure;
    private String arrival;
    private String departureDate;
    private String arrivalDate;

}
