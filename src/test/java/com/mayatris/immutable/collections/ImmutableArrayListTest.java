package com.mayatris.immutable.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;


public class ImmutableArrayListTest extends AbstractImmutableListTest {


    @Override
    public void setupList() {
        this.listInstance = new ImmutableArrayList<>();
    }


    @Test
    public void buildListFromValues() {
        listInstance = ImmutableArrayList.fromValues("hello", "goodbye");

        assertThat(listInstance.size()).isEqualTo(2);
        assertThat(listInstance).hasSize(2);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("hello", "goodbye");
    }


    @Test
    public void buildListFromJavaCollection() {
        Collection<String> collection = Arrays.asList("one", "two", "three");
        listInstance = ImmutableArrayList.from(collection);

        assertThat(listInstance.size()).isEqualTo(3);
        assertThat(listInstance).hasSize(3);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("one", "two", "three");
    }

    @Test
    public void buildListFromJavaIterator() {

        Iterator<String> iterator = newTestIterator();


        listInstance = ImmutableArrayList.from(iterator);

        assertThat(listInstance.size()).isEqualTo(5);
        assertThat(listInstance).hasSize(5);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("0", "1", "2", "3", "4");
    }

    private Iterator<String> newTestIterator() {
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