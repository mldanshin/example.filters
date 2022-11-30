package com.example.testing;

import com.example.testing.filters.Filter;

import java.util.ArrayList;
import java.util.List;

public class FilterManager {
    public List<Flight> filter(List<Flight> flights, List<Filter> filters) {
        List<Flight> result = new ArrayList<>();

        if (flights == null) {
            return result;
        }

        if (filters == null || filters.isEmpty()) {
            result.addAll(flights);
            return result;
        }

        flights.forEach(item -> {
            if (!checkFilter(item, filters)) {
                result.add(item);
            }
        });

        return result;
    }

    private boolean checkFilter(Flight flight, List<Filter> filters) {
        for (Filter filter : filters) {
            if (filter.check(flight)) {
                return true;
            }
        }
        return false;
    }
}
