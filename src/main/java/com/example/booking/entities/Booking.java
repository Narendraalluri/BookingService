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

    @ManyToOne(cascade = CascadeType.MERGE)
    private Passenger passenger;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOKING_FLIGHT",
            joinColumns = @JoinColumn(name = "BOOKING_ID"),
            inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID")
    )
    private List<Flight> flights = new ArrayList<>();

    public Booking(Passenger passenger, List<Flight> flights) {
        this.passenger = passenger;
        this.flights = flights;
    }
}
