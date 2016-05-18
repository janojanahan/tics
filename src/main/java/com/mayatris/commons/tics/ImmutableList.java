package com.mayatris.commons.tics;

import java.util.*;

public interface ImmutableList<T> extends ImmutableCollection<T> {

    @SafeVarargs
    static <T> ImmutableList<T> fromValues(T... values) {
        return new ImmutableArrayList<>(values);
    }

    @SuppressWarnings("unchecked")
    static <T> ImmutableList<T> from(Collection<T> collection) {
        return fromValues((T[]) collection.toArray());
    }

    static <T> ImmutableList<T> from(Iterator<T> iterator) {
        List<T> copy = new LinkedList<>();
        while (iterator.hasNext())
            copy.add(iterator.next());
        return from(copy);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
     */
    T get(int index);

    @Override
    default Optional<T> head() {
        return (isEmpty() ? Optional.empty() : Optional.of(get(0)));
    }
}
