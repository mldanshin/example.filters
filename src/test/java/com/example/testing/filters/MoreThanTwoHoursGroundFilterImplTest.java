package com.example.testing.filters;

import com.example.testing.Flight;
import com.example.testing.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class MoreThanTwoHoursGroundFilterImplTest {
    private MoreThanTwoHoursGroundFilterImpl filter;

    @BeforeEach
    void setUp() {
        filter = new MoreThanTwoHoursGroundFilterImpl();
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
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
                        new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(13))
                )
        )));

        Assertions.assertFalse(filter.check(new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
                        new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(13)),
                        new Segment(LocalDateTime.now().plusHours(14), LocalDateTime.now().plusHours(16))
                )
        )));
    }

    @Test
    void checkTrue() {
        Assertions.assertTrue(filter.check(new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
                        new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(13))
                )
        )));

        Assertions.assertTrue(filter.check(new Flight(
                List.of(
                        new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3)),
                        new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5)),
                        new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(7)),
                        new Segment(LocalDateTime.now().plusHours(8), LocalDateTime.now().plusHours(8))
                )
        )));
    }
}