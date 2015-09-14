package com.mayatris.commons.tics;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public abstract class AbstractImmutableCollectionTest {


    protected abstract ImmutableCollection<String> getCollectionInstance();

    protected abstract ImmutableCollection<String> getCollectionInstanceFromValues(String... values);

    @Test
    public void addItems() {
        ImmutableCollection<String> collectionInstance = getCollectionInstanceFromValues("item1");

        assertThat(collectionInstance.size()).isEqualTo(1);
        assertThat(collectionInstance).hasSize(1);
        assertThat(collectionInstance).isNotEmpty();
        assertThat(collectionInstance).containsExactly("item1");

        collectionInstance = collectionInstance.add("item2");

        assertThat(collectionInstance.size()).isEqualTo(2);
        assertThat(collectionInstance).hasSize(2);
        assertThat(collectionInstance).isNotEmpty();
        assertThat(collectionInstance).containsExactly("item1", "item2");
    }

    @Test
    public void addNullThrowsException() {

        ImmutableCollection<String> collectionInstance = getCollectionInstance();
        assertThatThrownBy(() -> collectionInstance.add(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void headOnEmptyListReturnsOptionalEmpty() {
        ImmutableCollection<String> collectionInstance = getCollectionInstance();
        assertThat(collectionInstance.head()).isEqualTo(Optional.empty());
    }

    @Test
    public void head() {
        ImmutableCollection<String> collectionInstance = getCollectionInstance();
        assertThat(collectionInstance.head()).isEmpty();

        collectionInstance = collectionInstance.add("test");
        assertThat(collectionInstance.head())
            .isPresent()
            .contains("test");
    }

    @Test
    public void headOrElse() {
        ImmutableCollection<String> collectionInstance = getCollectionInstance();
        assertThat(collectionInstance.headOrElse("other")).isEqualTo("other");

        collectionInstance = collectionInstance.add("head");
        assertThat(collectionInstance.headOrElse("other")).isEqualTo("head");
    }

    @Test
    public void tail() {
        ImmutableCollection<String> collectionInstance = getCollectionInstance();
        assertThat(collectionInstance.tail().isEmpty());

        collectionInstance = collectionInstance.add("item1");
        assertThat(collectionInstance.tail().isEmpty());

        collectionInstance = collectionInstance.add("item2");
        assertThat(collectionInstance.<ImmutableCollection<String>>tail())
            .containsOnly("item2")
            .hasSize(1);

    }

    @Test
    public void tailOrElse() {
        ImmutableCollection<String> collectionInstance = getCollectionInstance();
        ImmutableList<String> other = ImmutableList.fromValues("a value");
        assertThat(collectionInstance.tailOrElse(other) == other);

    }

    @Test
    public void addAllImmutableCollection() {
        final ImmutableCollection<String> collectionInstance = getCollectionInstanceFromValues("a value 1", "a value 2");
        final ImmutableList<String> other = ImmutableList.fromValues("b value 1", "b value 2");

        ImmutableCollection<String> mergedCollection = collectionInstance.addAll(other);
        assertThat(mergedCollection)
            .hasSize(4)
            .containsExactly("a value 1", "a value 2", "b value 1", "b value 2");
    }


    @Test
    public void addAllCollection() {
        final ImmutableCollection<String> collectionInstance = getCollectionInstanceFromValues("a value 1", "a value 2");
        final Collection<String> other = Arrays.asList(new String[]{"b value 1", "b value 2"});

        ImmutableCollection<String> mergedCollection = collectionInstance.addAll(other);
        assertThat(mergedCollection)
            .hasSize(4)
            .containsExactly("a value 1", "a value 2", "b value 1", "b value 2");
    }

    @Test
    public void addAllIterable() {
        final ImmutableCollection<String> collectionInstance = getCollectionInstanceFromValues("a value 1", "a value 2");
        final Iterable<String> other = Arrays.asList(new String[]{"b value 1", "b value 2"});

        ImmutableCollection<String> mergedCollection = collectionInstance.addAll(other);
        assertThat(mergedCollection)
            .hasSize(4)
            .containsExactly("a value 1", "a value 2", "b value 1", "b value 2");
    }

    @Test
    public void addAllIterator() {
        final ImmutableCollection<String> collectionInstance = getCollectionInstanceFromValues("a value 1", "a value 2");
        final Iterator<String> other = newTestIterator();

        ImmutableCollection<String> mergedCollection = collectionInstance.addAll(other);
        assertThat(mergedCollection)
            .hasSize(7)
            .containsExactly("a value 1", "a value 2", "0", "1", "2", "3", "4");
    }

    @Test
    public void addAllValues() {
        final ImmutableCollection<String> collectionInstance = getCollectionInstanceFromValues("a value 1", "a value 2");

        ImmutableCollection<String> mergedCollection = collectionInstance.addAll("a", "b", "c");
        assertThat(mergedCollection)
            .hasSize(5)
            .containsExactly("a value 1", "a value 2", "a", "b", "c");
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
