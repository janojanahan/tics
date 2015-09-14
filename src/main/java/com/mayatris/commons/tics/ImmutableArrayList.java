package com.mayatris.commons.tics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
    public ImmutableArrayList<T> tail() {
        return size() > 1 ? subList(1, size() - 1) : new ImmutableArrayList<>();
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public <K extends ImmutableCollection<T>> K add(T item) {
        Objects.requireNonNull(item);
        ImmutableArrayList<T> newList = new ImmutableArrayList<>(size() + 1);

        System.arraycopy(data, 0, newList.data, 0, data.length);
        newList.data[data.length] = item;
        return (K) newList;
    }

    private ImmutableArrayList<T> subList(int startIndex, int size) {
        //Note no range checks as is a private method, calling methos should assume values are correct.
        ImmutableArrayList<T> newList = new ImmutableArrayList<>(size);
        System.arraycopy(data, startIndex, newList.data, 0, size);
        return newList;
    }

    @Override
    public Iterator<T> iterator() {
        return new ImmutableArrayListIterator();
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
