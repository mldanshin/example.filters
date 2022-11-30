package com.example.testing.context;

import com.example.testing.filters.MoreThanTwoHoursGroundFilterImpl;

import java.util.ArrayList;

public class ContextCImpl extends Context {
    @Override
    protected void initFilters() {
        filters = new ArrayList<>();
        filters.add(new MoreThanTwoHoursGroundFilterImpl());
    }
}
