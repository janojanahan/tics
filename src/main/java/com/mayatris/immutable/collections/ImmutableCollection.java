package com.mayatris.immutable.collections;

import java.util.Optional;

public interface ImmutableCollection<E> extends Iterable<E> {

    /**
     * Get the head of the list
     *
     * @return Head element or null if empty
     */
    Optional<E> head();

    default E headOrElse(E other) {
        return other;
    }

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

    /**
     * test if empty
     *
     * @return
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * <p> Returns a new colleciton of the same type that contains the item added. If the collection does
     * not allow duplicates, it returns the same collection.</p>
     * <p>>Collections that support this operation may place limitations on what elements may be added to this
     * collection. In particular, some collections will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added. Collection classes should clearly specify in their
     * documentation any restrictions on what elements may be added.</P
     *
     * @param item Item to be added.
     */
    <K extends ImmutableCollection<E>> K add(E item);
}
