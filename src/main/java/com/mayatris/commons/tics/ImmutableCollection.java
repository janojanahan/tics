package com.mayatris.commons.tics;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface ImmutableCollection<E> extends Iterable<E> {

    /**
     * Get the head of the list
     *
     * @return Head element or null if empty
     */
    Optional<E> head();

    /**
     * Get the head element if there are items in the list, otherwise return "other"
     */
    default E headOrElse(E other) {
        return head().orElse(other);
    }

    /**
     * get the rest of the list with the head removed. Returns an empty list if no items remain.
     *
     * @return tail or null if not present
     */
    <K extends ImmutableCollection<E>> K tail();

    /**
     * get the rest of the list with the head removed. Returns other if rest of the list would be empty
     * @param other
     * @param <K>
     * @return
     */
    default <K extends ImmutableCollection<E>> K tailOrElse(K other){
        K tail = tail();
        return (tail.isEmpty() ? other : tail);
    }

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



    <K extends ImmutableCollection<E>> K addAll(ImmutableCollection<E> items);

    default <K extends ImmutableCollection<E>> K addAll(Collection<E> items) {
        return addAll((E[]) items.toArray());
    }

    default <K extends ImmutableCollection<E>> K addAll(Iterable<E> items) {
        return addAll(items.iterator());
    }

    default <K extends ImmutableCollection<E>> K addAll(Iterator<E> items) {
        List<E> tempList = new LinkedList<>();
        items.forEachRemaining(e-> tempList.add(e));
        E[] tempArray = (E[]) tempList.toArray();
        return addAll(tempArray);
    }

    <K extends ImmutableCollection<E>> K addAll(E... items);
}
