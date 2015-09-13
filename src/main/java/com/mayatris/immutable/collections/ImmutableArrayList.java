package com.mayatris.immutable.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by jano on 10/09/15.
 */
public class ImmutableArrayList<T> implements ImmutableList<T> {

    private T[] data;

    public ImmutableArrayList() {
        this(0);
    }

    private ImmutableArrayList(int size) {
        data = newArray(size);
    }

    ImmutableArrayList(T... values) {
        data = values;
    }

    private T[] newArray(int i) {
        return (T[]) new Object[i];
    }

    @Override
    public Optional<T> head() {
        if (isEmpty()) return Optional.empty();
        throw new Error("Not implemented");
    }

    @Override
    public <K extends ImmutableCollection<T>> Optional<K> tail() {
        throw new Error("Not implemented");
    }

    @Override
    public int size() {
        return data.length;
    }


    @Override
    public Iterator<T> iterator() {
        return new ImmutableArrayListIterator();
    }

    @Override
    public <K extends ImmutableCollection<T>> K add(T item) {
        Objects.requireNonNull(item);
        ImmutableArrayList<T> newList = new ImmutableArrayList<>(size() + 1);

        System.arraycopy(data, 0, newList.data, 0, data.length);
        newList.data[data.length] = item;
        return (K) newList;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index > data.length - 1)) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds to the size of this collection(%d)",
                            index, data.length));
        }
        return data[index];
    }

    private class ImmutableArrayListIterator implements Iterator<T> {

        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < data.length;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return data[position++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove from immutable iterator");
        }
    }

}