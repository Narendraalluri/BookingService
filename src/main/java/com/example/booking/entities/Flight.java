package com.example.booking.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String departure;
    private String arrival;
    private String departureDate;
    private String arrivalDate;

    @ManyToMany(mappedBy = "flights")
    private List<Booking> bookings;

    public Flight(String departure, String arrival, String departureDate, String arrivalDate) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

}
