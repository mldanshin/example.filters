package com.example.testing;

import java.util.List;

public class Display {
    public static void print(List<Flight> flights) {
        flights.forEach(flight -> {
            System.out.println("Flight");
            flight.getSegments().forEach(segment -> {
                System.out.print("departure " + segment.getDepartureDate().toString() + " ");
                System.out.println("arrival " + segment.getArrivalDate().toString());
            });
            System.out.println("");
        });
        System.out.println("---------------");
    }
}
