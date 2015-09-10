package com.mayatris.immutable.collections;

import java.util.Optional;

public interface ImmutableCollection<E> extends Iterable<E> {

    /**
     * Get the head of the list
     *
     * @return Head element or null if empty
     */
    Optional<E> head();

    /**
     * get the rest of the list with the head removed
     *
     * @return tail or null if not present
     */
    <K extends ImmutableCollection<E>> Optional<K> tail();

    /**
     * Returns size of the colleciton
     */

    int size();
}
