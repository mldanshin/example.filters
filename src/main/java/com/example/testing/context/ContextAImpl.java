package com.example.testing.context;

import com.example.testing.filters.BeforeCurrentFilterImpl;

import java.util.ArrayList;

public class ContextAImpl extends Context {
    @Override
    protected void initFilters() {
        filters = new ArrayList<>();
        filters.add(new BeforeCurrentFilterImpl());
    }
}
