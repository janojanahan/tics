package com.mayatris.commons.tics;

import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class AbstractArrayStore<T> implements ToJava<T> {

    protected T[] data;

    protected AbstractArrayStore() {
        this(0);
    }

    protected AbstractArrayStore(int size) {
        data = newArray(size);
    }

    protected AbstractArrayStore(T[] values) {
        data = values;
    }

    @SuppressWarnings("unchecked")
    protected T[] newArray(int i) {
        return (T[]) new Object[i];
    }

    protected class ArrayStoreIterator implements Iterator<T> {

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
            throw new UnsupportedOperationException("Cannot remove from an immutable iterator");
        }
    }
}
