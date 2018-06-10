package com.example.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Passenger {

    private String firstName;
    private String lastName;
    private String email;

    @JsonIgnore
    private String passengerId;

    @JsonIgnore
    public String getId() {
        return this.passengerId;
    }

    @JsonIgnore
    public String getPassengerId() {
        return this.passengerId;
    }
}
