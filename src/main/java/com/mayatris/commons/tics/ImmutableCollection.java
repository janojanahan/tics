package com.mayatris.commons.tics;

import java.util.*;

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
     */
    default <K extends ImmutableCollection<E>> K tailOrElse(K other){
        K tail = tail();
        return (tail.isEmpty() ? other : tail);
    }

    /**
     * Returns size of the collection
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
     * <p> Returns a new collection of the same type that contains the item added. If the collection does
     * not allow duplicates, it returns the same collection.</p>
     * <p>>Collections that support this operation may place limitations on what elements may be added to this
     * collection. In particular, some collections will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added. Collection classes should clearly specify in their
     * documentation any restrictions on what elements may be added.</P
     *
     * @param item Item to be added.
     */
    <K extends ImmutableCollection<E>> K add(E item);



    default <K extends ImmutableCollection<E>> K addAll(ImmutableCollection<E> items) {
        Objects.requireNonNull(items, "Paramter items, cannot be null");
        return addAll(items.iterator());
    }

    @SuppressWarnings("unchecked")
    default <K extends ImmutableCollection<E>> K addAll(Collection<E> items) {
        Objects.requireNonNull(items, "Paramter items, cannot be null");
        //noinspection unchecked
        return addAll((E[]) items.toArray());
    }

    default <K extends ImmutableCollection<E>> K addAll(Iterable<E> items) {
        Objects.requireNonNull(items, "Paramter items, cannot be null");
        return addAll(items.iterator());
    }

    @SuppressWarnings("unchecked")
    default <K extends ImmutableCollection<E>> K addAll(Iterator<E> items) {
        Objects.requireNonNull(items, "Paramter items, cannot be null");
        List<E> tempList = new LinkedList<>();
        items.forEachRemaining(tempList::add);
        E[] tempArray = (E[]) tempList.toArray();
        return addAll(tempArray);
    }

    <K extends ImmutableCollection<E>> K addAll(E... items);

    <K extends ImmutableCollection<E>> K remove(E object);

//    <K extends ImmutableCollection<E>> K removeAll(E... items);
//    <K extends ImmutableCollection<E>> K removeAll(ImmutableCollection<E> items);
//    <K extends ImmutableCollection<E>> K removeAll(Iterator<E> items);
//    <K extends ImmutableCollection<E>> K removeAll(Iterable<E> items);
//    <K extends ImmutableCollection<E>> K removeAll(Collection<E> items);

}
