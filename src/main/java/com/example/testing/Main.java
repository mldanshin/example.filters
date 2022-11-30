package com.example.testing;

import com.example.testing.filters.Filter;
import com.example.testing.context.ContextAImpl;
import com.example.testing.context.ContextBImpl;
import com.example.testing.context.ContextCImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        filter(flights, new ContextAImpl().getFilters());
        filter(flights, new ContextBImpl().getFilters());
        filter(flights, new ContextCImpl().getFilters());
    }

    private static void filter(List<Flight> flights, List<Filter> filters) {
        List<Flight> filteredFlights = new FilterManager().filter(flights, filters);
        Display.print(filteredFlights);
    }
}
