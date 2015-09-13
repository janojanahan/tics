package com.mayatris.immutable.collections;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
        assertThat(listInstance).hasSize(1);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("item1");

        listInstance = listInstance.add("item2");

        assertThat(listInstance.size()).isEqualTo(2);
        assertThat(listInstance).hasSize(2);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("item1", "item2");
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

    @Test
    public void head() {
        assertThat(listInstance.head()).isEmpty();

        listInstance = listInstance.add("test");
        assertThat(listInstance.head())
                .isPresent()
                .contains("test");
    }

    @Test
    public void headOrElse() {
        assertThat(listInstance.headOrElse("other")).isEqualTo("other");

        listInstance = listInstance.add("head");
        assertThat(listInstance.headOrElse("other")).isEqualTo("head");
    }

    @Test
    public void tail() {
        assertThat(listInstance.tail().isEmpty());

        listInstance = listInstance.add("item1");
        assertThat(listInstance.tail().isEmpty());

        listInstance = listInstance.add("item2");
        assertThat(listInstance.<ImmutableCollection<String>>tail())
            .containsOnly("item2")
            .hasSize(1);

    }

    @Test
    public void tailOrElse() {
        ImmutableList<String> other = ImmutableList.fromValues("a value");
        assertThat(listInstance.tailOrElse(other) == other);


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
