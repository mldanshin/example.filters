package com.example.testing.context;

import com.example.testing.filters.Filter;

import java.util.List;

public abstract class Context {
    protected List<Filter> filters;

    public Context() {
        initFilters();
    }

    public List<Filter> getFilters() {
        return filters;
    }

    protected abstract void initFilters();
}
