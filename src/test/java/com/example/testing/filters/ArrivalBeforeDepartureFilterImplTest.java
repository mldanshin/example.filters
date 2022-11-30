package com.example.testing.filters;

import com.example.testing.Flight;
import com.example.testing.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class ArrivalBeforeDepartureFilterImplTest {
    private ArrivalBeforeDepartureFilterImpl filter;

    @BeforeEach
    void setUp() {
        filter = new ArrivalBeforeDepartureFilterImpl();
    }

    @Test
    void checkNull() {
        Assertions.assertThrows(NullPointerException.class, () -> filter.check(null));
    }

    @Test
    void checkFalse() {
        Assertions.assertFalse(filter.check(new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1))
                )
        )));

        Assertions.assertFalse(filter.check(new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now())
                )
        )));

        Assertions.assertFalse(filter.check(new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now()),
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1))
                )
        )));
    }

    @Test
    void checkTrue() {
        Assertions.assertTrue(filter.check(new Flight(
                List.of(
                        new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().minusHours(1))
                )
        )));

        Assertions.assertTrue(filter.check(new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now()),
                        new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().minusHours(1)),
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1))
                )
        )));
    }
}