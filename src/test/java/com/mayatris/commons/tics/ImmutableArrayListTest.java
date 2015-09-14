package com.mayatris.commons.tics;

public class ImmutableArrayListTest extends AbstractImmutableListTest {


    @Override
    protected ImmutableList<String> getListInstance() {
        return new ImmutableArrayList<>();
    }

    @Override
    protected ImmutableList<String> getListInstanceFromValues(String... values) {
        return new ImmutableArrayList<>(values);
    }

    @Override
    protected ImmutableCollection<String> getCollectionInstance() {
        return getListInstance();
    }

    @Override
    protected ImmutableCollection<String> getCollectionInstanceFromValues(String... values) {
        return getListInstanceFromValues(values);
    }
}