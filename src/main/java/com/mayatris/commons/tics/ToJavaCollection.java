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
     * Transform this collection to a Java map using the provided mapping function. The map type will be
     * chosen by the implmentation
     */
    default <KEY> Map<KEY,T> transformToMap(Function<T, Tuple2<KEY, T>> mapFunction) {
        return transformAndAddToMap(mapFunction, new HashMap<>());
    }

    /**
     * Transform this collection into key value pairs using the provided mapping function,
     * then adding values to the provided map.
     * @return original instance of map provided in parameter.
     */
    <KEY> Map<KEY,T> transformAndAddToMap(Function<T, Tuple2<KEY,T>> mapFunction, HashMap<KEY, T> map);
}
