package com.mayatris.immutable.collections;

public interface ImmutableCollection<E> extends Iterable<E> {

    /**
     * Get the head of the list
     *
     * @return Head element or null if empty
     */
    E head();

    /**
     * get the rest of the list with the head removed
     *
     * @return tail or null if not present
     */
    ImmutableCollection<E> tail();


    Tuple2<E, ImmutableCollection<E>> splitAtHead();

}
