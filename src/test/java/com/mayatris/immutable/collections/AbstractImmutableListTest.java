package com.mayatris.immutable.collections;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;

public abstract class AbstractImmutableListTest {

    protected ImmutableList<String> listInstance;

    @Before
    public abstract void setupList();

    @Test
    public void testNewListDefaults() {
        assertThat(listInstance.size()).isEqualTo(0);
        assertThat(listInstance).hasSize(0);
        assertThat(listInstance).isEmpty();
    }


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

}
