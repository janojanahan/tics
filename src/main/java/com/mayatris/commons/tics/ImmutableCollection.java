package com.mayatris.commons.tics;

import java.util.*;

public interface ImmutableCollection<E> extends Iterable<E>, ToJavaCollection<E> {

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
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * <p> Returns a new immutable collection of the same type that contains the item added. If the collection does
     * not allow duplicates, it returns the same collection.</p>
     * <p>> Immutable Collections that support this operation may place limitations on what elements may be added to this
     * collection. In particular, some collections will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added. Collection classes should clearly specify in their
     * documentation any restrictions on what elements may be added.</P
     *
     * @param item Item to be added.
     */
    <K extends ImmutableCollection<E>> K add(E item);


    /**
     * <p> Returns a new immutable collection of the same type that contains the items added. If the collection does
     * not allow duplicates, it returns an immutable collection with only non duplicates added.</p>
     * <p>> Immutable Collections that support this operation may place limitations on what elements may be added to this
     * collection. In particular, some collections will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added. Collection classes should clearly specify in their
     * documentation any restrictions on what elements may be added.</P
     *
     * @param items Items to be added.
     */
    default <K extends ImmutableCollection<E>> K addAll(ImmutableCollection<E> items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        return addAll(items.iterator());
    }

    @SuppressWarnings("unchecked")
    default <K extends ImmutableCollection<E>> K addAll(Collection<E> items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        //noinspection unchecked
        return addAll((E[]) items.toArray());
    }

    default <K extends ImmutableCollection<E>> K addAll(Iterable<E> items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        return addAll(items.iterator());
    }

    @SuppressWarnings("unchecked")
    default <K extends ImmutableCollection<E>> K addAll(Iterator<E> items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        List<E> tempList = new LinkedList<>();
        items.forEachRemaining(tempList::add);
        E[] tempArray = (E[]) tempList.toArray();
        return addAll(tempArray);
    }

    @SuppressWarnings("unchecked")
    <K extends ImmutableCollection<E>> K addAll(E... items);

    <K extends ImmutableCollection<E>> K remove(E object);

    @SuppressWarnings("unchecked")
    <K extends ImmutableCollection<E>> K removeAll(E... items);

    default <K extends ImmutableCollection<E>> K removeAll(ImmutableCollection<E> items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        return removeAll(items.iterator());
    }

    @SuppressWarnings("unchecked")
    default <K extends ImmutableCollection<E>> K removeAll(Iterator<E> items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        List<E> tempList = new LinkedList<>();
        items.forEachRemaining(tempList::add);
        E[] tempArray = (E[]) tempList.toArray();
        return removeAll(tempArray);
    }

    default <K extends ImmutableCollection<E>> K removeAll(Iterable<E> items){
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        return removeAll(items.iterator());
    }

    default <K extends ImmutableCollection<E>> K removeAll(Collection<E> items) {
        Objects.requireNonNull(items, "Parameter items, cannot be null");
        return removeAll(items.iterator());
    }

}
