package com.mayatris.commons.tics;

import java.util.*;
import java.util.function.Function;

public class ImmutableArrayList<T> implements ImmutableList<T> {

    private T[] data;

    public ImmutableArrayList() {
        this(0);
    }

    private ImmutableArrayList(int size) {
        data = newArray(size);
    }

    @SuppressWarnings("unchecked")
    private T[] newArray(int i) {
        return (T[]) new Object[i];
    }

    @SafeVarargs
    public ImmutableArrayList(T... values) {
        data = values.clone();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ImmutableArrayList<T> tail() {
        return size() > 1 ? subList(1, size() - 1) : new ImmutableArrayList<>();
    }

    @Override
    public int size() {
        return data.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <K extends ImmutableCollection<T>> K add(T item) {
        Objects.requireNonNull(item);
        ImmutableArrayList<T> newList = new ImmutableArrayList<>(size() + 1);

        System.arraycopy(data, 0, newList.data, 0, data.length);
        newList.data[data.length] = item;
        //noinspection unchecked
        return (K) newList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <K extends ImmutableCollection<T>> K addAll(ImmutableCollection<T> items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        if (items instanceof ImmutableArrayList) {
            ImmutableArrayList<T> source = (ImmutableArrayList<T>) items;
            return addAll(source.data);
        } else {
            return addAll(items.iterator());
        }
    }



    @Override
    @SafeVarargs
    public final <K extends ImmutableCollection<T>> K addAll(T... items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        ImmutableArrayList<T> newList = new ImmutableArrayList<>(items.length + data.length);
        System.arraycopy(data,0,newList.data, 0, data.length);
        System.arraycopy(items,0,newList.data, data.length, items.length);
        //noinspection unchecked
        return (K) newList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K extends ImmutableCollection<T>> K remove(T object) {
        Objects.requireNonNull(object);
        ImmutableArrayList<T> newList = new ImmutableArrayList<>();
        newList.data = (T[]) Arrays.stream(data).filter(s -> !s.equals(object)).toArray();

        return (K) newList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K extends ImmutableCollection<T>> K removeAll(T... items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        Set<T> itemsToRemove = new HashSet<>();
        Collections.addAll(itemsToRemove, items);

        T[] newData = (T[])Arrays.stream(data)
            .filter(s -> !itemsToRemove.contains(s))
                .toArray();
        return (K) new ImmutableArrayList<>(newData);

    }


    private ImmutableArrayList<T> subList(int startIndex, int size) {
        ImmutableArrayList<T> newList = new ImmutableArrayList<>(size);
        System.arraycopy(data, startIndex, newList.data, 0, size);
        return newList;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayStoreIterator();
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

    @Override
    public Set<T> toJavaSet(Set<T> set) {
        Collections.addAll(set, data);
        return set;
    }

    @Override
    public List<T> toJavaList(List<T> list) {
        Collections.addAll(list, data);
        return list;
    }

    @Override
    public <KEY> Map<KEY, T> transformAndAddToMap(Function<T, Tuple2<KEY, T>> mapFunction, HashMap<KEY, T> map) {
        for(T i: data) {
            final Tuple2<KEY, T> tuple = mapFunction.apply(i);
            map.put(tuple.one(), tuple.two());
        }
        return map;
    }

    private class ArrayStoreIterator implements Iterator<T> {

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
