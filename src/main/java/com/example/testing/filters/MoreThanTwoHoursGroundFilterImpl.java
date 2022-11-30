package com.example.testing.filters;

import com.example.testing.Flight;
import com.example.testing.Segment;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class MoreThanTwoHoursGroundFilterImpl implements Filter {
    @Override
    public boolean check(Flight f) {
        Flight flight = Objects.requireNonNull(f);
        List<Segment> segments = flight.getSegments();
        Segment previousSegment = null;
        long groundTime = 0;

        for (Segment segment : segments) {
            if (previousSegment != null) {
                groundTime += ChronoUnit.HOURS.between(previousSegment.getArrivalDate(), segment.getDepartureDate());
            }
            previousSegment = segment;
        }

        return groundTime > 2;
    }
}
