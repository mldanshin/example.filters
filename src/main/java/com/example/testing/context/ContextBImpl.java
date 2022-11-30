package com.example.testing.context;

import com.example.testing.filters.ArrivalBeforeDepartureFilterImpl;

import java.util.ArrayList;

public class ContextBImpl extends Context {
    @Override
    protected void initFilters() {
        filters = new ArrayList<>();
        filters.add(new ArrivalBeforeDepartureFilterImpl());
    }
}
