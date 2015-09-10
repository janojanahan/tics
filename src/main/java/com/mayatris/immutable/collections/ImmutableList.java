package com.mayatris.immutable.collections;

public interface ImmutableList<T> extends ImmutableCollection<T> {


    <K extends ImmutableList<T>> K add(T item);
}
