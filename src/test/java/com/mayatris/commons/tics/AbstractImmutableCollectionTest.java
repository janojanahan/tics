package com.mayatris.commons.tics;

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

    
    protected abstract ImmutableCollection<String> getCollectionInstance();
    
    protected abstract ImmutableCollection<String> getCollectionInstanceFromValues(String... values);

    @Before
    public abstract void setupList();

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
        assertThatThrownBy(() -> collectionInstance.add(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void headOnEmptyListReturnsOptionalEmpty() {
        assertThat(collectionInstance.head()).isEqualTo(Optional.empty());
    }

    @Test
    public void head() {
        assertThat(collectionInstance.head()).isEmpty();

        collectionInstance = collectionInstance.add("test");
        assertThat(collectionInstance.head())
                .isPresent()
                .contains("test");
    }

    @Test
    public void headOrElse() {
        assertThat(collectionInstance.headOrElse("other")).isEqualTo("other");

        collectionInstance = collectionInstance.add("head");
        assertThat(collectionInstance.headOrElse("other")).isEqualTo("head");
    }

    @Test
    public void tail() {
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
        ImmutableList<String> other = ImmutableList.fromValues("a value");
        assertThat(collectionInstance.tailOrElse(other) == other);
        
    }
    
    @Test
    public void addAllImmutableCollection() {
        collectionInstance = collectionInstance.add("a value 1", "a value 2");
        ImmutableList<String> other = ImmutableList.fromValues("b value 1", "b value 2");
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
