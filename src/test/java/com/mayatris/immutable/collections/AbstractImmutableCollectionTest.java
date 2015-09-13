package com.mayatris.immutable.collections;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;

/**
 * Created by jano on 13/09/15.
 */
public abstract class AbstractImmutableCollectionTest {
    protected ImmutableList<String> listInstance;

    @Before
    public abstract void setupList();

    @Test
    public void addItems() {
        listInstance = listInstance.add("item1");

        assertThat(listInstance.size()).isEqualTo(1);
        Assertions.assertThat(listInstance).hasSize(1);
        Assertions.assertThat(listInstance).isNotEmpty();
        Assertions.assertThat(listInstance).containsExactly("item1");

        listInstance = listInstance.add("item2");

        assertThat(listInstance.size()).isEqualTo(2);
        Assertions.assertThat(listInstance).hasSize(2);
        Assertions.assertThat(listInstance).isNotEmpty();
        Assertions.assertThat(listInstance).containsExactly("item1", "item2");
    }

    @Test
    public void addNullThrowsException() {
        assertThatThrownBy(() -> listInstance.add(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void headOnEmptyListReturnsOptionalEmpty() {
        assertThat(listInstance.head()).isEqualTo(Optional.empty());
    }

    protected Iterator<String> newTestIterator() {
        return new Iterator<String>() {

            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < 5;
            }

            @Override
            public String next() {
                return "" + (count++);
            }
        };
    }
}
