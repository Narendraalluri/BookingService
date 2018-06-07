package com.example.booking.resources;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "", produces = MediaTypes.HAL_JSON_VALUE)
public class RootController {

    @GetMapping("")
    public ResourceSupport getResourceLinks() {
        ResourceSupport resource = new ResourceSupport();
        resource.add(
                linkTo(methodOn(RootController.class)
                        .getResourceLinks())
                        .withRel("_self"));

        resource.add(
                new Link(linkTo(RootController.class).toString() + "/swagger-ui.html"
                        , "Swagger Documentation"));

        resource.add(
                linkTo(methodOn(BookingController.class)
                        .getPassengerBookings(null))
                        .withRel("passengerBookings"));

        resource.add(
                linkTo(methodOn(BookingController.class)
                        .getBookingDetails(null))
                        .withRel("bookingDetails"));

        return resource;
    }

}
