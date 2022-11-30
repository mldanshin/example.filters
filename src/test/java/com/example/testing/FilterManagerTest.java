package com.example.testing;

import com.example.testing.filters.ArrivalBeforeDepartureFilterImpl;
import com.example.testing.filters.BeforeCurrentFilterImpl;
import com.example.testing.filters.Filter;
import com.example.testing.filters.MoreThanTwoHoursGroundFilterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class FilterManagerTest {
    FilterManager filterManager;
    List<Flight> flights;
    Flight flightNormal;
    Flight flightNormalMulti;
    Flight flightDepartingPast;
    Flight flightDepartsBeforeArrives;
    Flight flightMoreThanTwoHoursGround1;
    Flight flightMoreThanTwoHoursGround2;

    @BeforeEach
    void setUp() {
        filterManager = new FilterManager();

        //A normal flight with two hour duration
        flightNormal = new Flight(
                List.of(
                        new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(5))
                )
        );

        //A normal multi segment flight
        flightNormalMulti = new Flight(
                List.of(
                        new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                        new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5))
                )
        );

        //A flight departing in the past
        flightDepartingPast = new Flight(
                List.of(
                        new Segment(LocalDateTime.now().minusHours(4), LocalDateTime.now().plusHours(1))
                )
        );

        //A flight that departs before it arrives
        flightDepartsBeforeArrives = new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now().minusHours(5))
                )
        );

        //A flight with more than two hours ground time
        flightMoreThanTwoHoursGround1 = new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)),
                        new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6))
                )
        );

        //Another flight with more than two hours ground time
        flightMoreThanTwoHoursGround2 = new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)),
                        new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4)),
                        new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(7))
                )
        );

        flights = List.of(
                flightNormal,
                flightNormalMulti,
                flightDepartingPast,
                flightDepartsBeforeArrives,
                flightMoreThanTwoHoursGround1,
                flightMoreThanTwoHoursGround2
        );
    }

    @Test
    void filterNull() {
        List<Flight> filteredFlights = filterManager.filter(flights, null);
        Assertions.assertEquals(6, filteredFlights.size());
        Assertions.assertIterableEquals(flights, filteredFlights);
    }

    @Test
    void filterNullAndFlightsNull() {
        List<Flight> filteredFlights = filterManager.filter(null, null);
        Assertions.assertEquals(0, filteredFlights.size());
    }

    @Test
    void filterEmpty() {
        List<Filter> filters = new ArrayList<>();

        List<Flight> filteredFlights = filterManager.filter(flights, filters);

        Assertions.assertEquals(6, filteredFlights.size());
        Assertions.assertIterableEquals(flights, filteredFlights);
    }

    @Test
    void filterContext1() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new ArrivalBeforeDepartureFilterImpl());
        filters.add(new BeforeCurrentFilterImpl());
        filters.add(new MoreThanTwoHoursGroundFilterImpl());

        List<Flight> filteredFlights = filterManager.filter(flights, filters);

        Assertions.assertEquals(2, filteredFlights.size());
        Assertions.assertIterableEquals(
                List.of(flightNormal, flightNormalMulti),
                filteredFlights
        );
    }

    @Test
    void filterContext2() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new ArrivalBeforeDepartureFilterImpl());
        filters.add(new MoreThanTwoHoursGroundFilterImpl());

        List<Flight> filteredFlights = filterManager.filter(flights, filters);

        Assertions.assertEquals(3, filteredFlights.size());
        Assertions.assertIterableEquals(
                List.of(flightNormal, flightNormalMulti, flightDepartingPast),
                filteredFlights
        );
    }

    @Test
    void filterContext3() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new MoreThanTwoHoursGroundFilterImpl());

        List<Flight> filteredFlights = filterManager.filter(flights, filters);

        Assertions.assertEquals(4, filteredFlights.size());
        Assertions.assertIterableEquals(
                List.of(flightNormal, flightNormalMulti, flightDepartingPast, flightDepartsBeforeArrives),
                filteredFlights
        );
    }
}