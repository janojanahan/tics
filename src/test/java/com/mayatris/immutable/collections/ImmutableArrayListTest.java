package com.mayatris.immutable.collections;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;


public class ImmutableArrayListTest extends AbstractImmutableListTest {


    @Override
    public void setupList() {
        this.listInstance = new ImmutableArrayList<>();
    }

    @Test
    public void newListFromValues() {
        listInstance = new ImmutableArrayList<>("hello", "goodbye");

        assertThat(listInstance.size()).isEqualTo(2);
        assertThat(listInstance).hasSize(2);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("hello", "goodbye");
    }

    @Test
    public void newListFromValuesNullThrowsException() {
        assertThatThrownBy(() -> new ImmutableArrayList<>("hello", null)).isInstanceOf(NullPointerException.class);
    }

}