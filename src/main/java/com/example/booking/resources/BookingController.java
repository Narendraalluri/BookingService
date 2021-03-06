package com.example.booking.resources;

import com.example.booking.model.Booking;
import com.example.booking.model.BookingDetails;
import com.example.booking.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/bookings", produces = MediaTypes.ALPS_JSON_VALUE)
public class BookingController {

    private BookingService bookingService;

    private ModelMapper modelMapper;

    @Autowired
    public BookingController(BookingService bookingService, ModelMapper modelMapper) {
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<Booking>> getPassengerBookings(@RequestParam(value="uid") String passengerId) {

        Optional<UUID> passengerUUID = getUUID(passengerId);
        if (!passengerUUID.isPresent()) {
            return new ResponseEntity<>(Collections.emptyList(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        List<Booking> bookings = passengerUUID.map(uuid -> convertEntityToDTO(
                bookingService.getBookingsForPassenger(passengerUUID.get()))).orElse(Collections.emptyList());

        return new ResponseEntity<>(bookings, new HttpHeaders(), HttpStatus.OK);


    }

    @GetMapping("/{booking-id}")
    public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable(value="booking-id") String bookingId) {

        Optional<UUID> bookingUUID = getUUID(bookingId);
        if (!bookingUUID.isPresent()) {
            BookingDetails bookingDetails = new BookingDetails();
            return new ResponseEntity<>(bookingDetails, HttpStatus.BAD_REQUEST);
        }
        Optional<com.example.booking.entities.Booking> bookingEntity = bookingService.getBookingDetails(bookingUUID.get());


        BookingDetails bookingDetails = bookingEntity.map(booking -> modelMapper.map(booking, BookingDetails.class))
                .orElse(null);

        HttpStatus status = bookingEntity.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(bookingDetails, new HttpHeaders(), status);
    }

    private List<Booking> convertEntityToDTO(List<com.example.booking.entities.Booking> bookingList) {
        return bookingList.stream()
                .map(booking -> {
                    Booking model = new Booking();
                    model.setBookingId(booking.getId().toString());
                    model.setDeparture(CollectionUtils.isEmpty(booking.getFlights()) ? null : booking.getFlights().get(0).getDeparture());
                    model.setLastName(booking.getPassenger().getLastName());
                    return model;
                })
                .collect(Collectors.toList());
    }

    private Optional<UUID> getUUID(String id) {
        try {
            return Optional.of(UUID.fromString(id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
