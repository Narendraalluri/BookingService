package com.example.booking.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Booking {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOKING_PASSENGER",
            joinColumns = @JoinColumn(name = "BOOKING_ID"),
            inverseJoinColumns = @JoinColumn(name = "PASSENGER_ID")
    )
    private List<Passenger> passengers;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOKING_FLIGHT",
            joinColumns = @JoinColumn(name = "BOOKING_ID"),
            inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID")
    )
    private List<Flight> flights = new ArrayList<>();

    public Booking(List<Passenger> passengers, List<Flight> flights) {
        this.passengers = passengers;
        this.flights = flights;
    }
}
