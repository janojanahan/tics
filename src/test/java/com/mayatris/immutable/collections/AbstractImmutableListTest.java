package com.mayatris.immutable.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;

public abstract class AbstractImmutableListTest extends AbstractImmutableCollectionTest {

    @Test
    public void testNewListDefaults() {
        assertThat(listInstance.size()).isEqualTo(0);
        assertThat(listInstance).hasSize(0);
        assertThat(listInstance).isEmpty();
    }


    @Test
    public void buildListFromValues() {
        listInstance = ImmutableList.fromValues("hello", "goodbye");

        assertThat(listInstance.size()).isEqualTo(2);
        assertThat(listInstance).hasSize(2);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("hello", "goodbye");
    }

    @Test
    public void buildListFromJavaCollection() {
        Collection<String> collection = Arrays.asList("one", "two", "three");
        listInstance = ImmutableList.from(collection);

        assertThat(listInstance.size()).isEqualTo(3);
        assertThat(listInstance).hasSize(3);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("one", "two", "three");
    }

    @Test
    public void buildListFromJavaIterator() {

        Iterator<String> iterator = newTestIterator();


        listInstance = ImmutableList.from(iterator);

        assertThat(listInstance.size()).isEqualTo(5);
        assertThat(listInstance).hasSize(5);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("0", "1", "2", "3", "4");
    }

    @Test
    public void testGet() {
        listInstance = ImmutableList.fromValues("hello", "goodbye");
        assertThat(listInstance.get(0)).isEqualTo("hello");
        assertThat(listInstance.get(1)).isEqualTo("goodbye");
    }

    @Test
    public void testGetIndexOutOFBounds() {
        assertThatThrownBy(() -> listInstance.get(0))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index 0 is out of bounds to the size of this collection(0)")
                .hasNoCause();

        listInstance = ImmutableList.fromValues("value1");

        assertThatThrownBy(() -> listInstance.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index 1 is out of bounds to the size of this collection(1)");
        assertThatThrownBy(() -> listInstance.get(-1))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index -1 is out of bounds to the size of this collection(1)");
    }

}
