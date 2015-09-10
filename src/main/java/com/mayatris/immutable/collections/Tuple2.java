package com.mayatris.immutable.collections;

import java.util.Objects;

/**
 * Created by jano on 25/08/15.
 */
public final class Tuple2<A, B> {
    private final A item1;
    private final B item2;

    public Tuple2(A item1, B item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public static <L1, L2> Tuple2<L1, L2> from(L1 a, L2 b) {
        return new Tuple2<L1, L2>(a, b);
    }

    public A getItem1() {
        return item1;
    }

    public B getItem2() {
        return item2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(item1, tuple2.item1) &&
                Objects.equals(item2, tuple2.item2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item1, item2);
    }

    @Override
    public String toString() {
        return "{item1=" + item1 +
                ", item2=" + item2 +
                '}';
    }
}
