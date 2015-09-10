package com.mayatris.immutable.collections;

import java.util.Iterator;
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

    public ImmutableArrayList(T... values) {
        this(values.length);
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) throw new NullPointerException();
            data[i] = values[i];
        }
    }

    private T[] newArray(int i) {
        return (T[]) new Object[i];
    }

    @Override
    public Optional<T> head() {
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
    public <K extends ImmutableList<T>> K add(T item) {
        if (item == null) throw new NullPointerException();
        ImmutableArrayList<T> newList = new ImmutableArrayList<>(size() + 1);

        System.arraycopy(data, 0, newList.data, 0, data.length);
        newList.data[data.length] = item;
        return (K) newList;
    }

    private class ImmutableArrayListIterator implements Iterator<T> {

        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < data.length;
        }

        @Override
        public T next() {
            return data[position++];
        }
    }

}
