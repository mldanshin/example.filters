package com.example.testing.filters;

import com.example.testing.Flight;
import com.example.testing.Segment;

import java.util.List;
import java.util.Objects;

public class ArrivalBeforeDepartureFilterImpl implements Filter {
    @Override
    public boolean check(Flight f) {
        Flight flight = Objects.requireNonNull(f);
        List<Segment> segments = flight.getSegments();
        for (Segment segment : segments) {
            if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
                return true;
            }
        }
        return false;
    }
}
