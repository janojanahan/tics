package com.mayatris.commons.tics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Applied to represent an entity that can export Java Collection types.
 * Created by jano on 22/09/15.
 */
public interface ToJavaCollection<T> {

    /**
     * Add all items in this entity to a Java Set. The subtype of the set will be chosen by the implementation.
     */
    default Set<T> toJavaSet() {
        return toJavaSet(new HashSet<>());
    }

    /**
     * Add all items in this entity to a provided JavaSet.
     */
    Set<T> toJavaSet(Set<T> set);

    /**
     * Return all items as a Java List, with the type chosen by the implementation
     */
    default List<T> toJavaList() {
        return toJavaList(new ArrayList<>());
    }

    /**
     * Get all items in this ImmutableCollection in the provided List object
     */
    List<T> toJavaList(List<T> list);

    /**
     * construct a map using the provided map function from this collection. The map type will be
     * chosen by the implmentation
     */
    default <KEY> Map<KEY,T> constructMap(Function<T, Tuple2<KEY, T>> mapFunction) {
        return constructMap(mapFunction, new HashMap<>());
    }

    /**
     * fill in the provided map with values constructed from this collections using the provided map function
     */
    <KEY> Map<KEY,T> constructMap(Function<T, Tuple2<KEY,T>> mapFunction, HashMap<KEY, T> map);
}
