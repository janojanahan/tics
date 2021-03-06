package com.mayatris.commons.tics;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.StrictAssertions.entry;

public abstract class AbstractImmutableListTest extends AbstractImmutableCollectionTest {

    protected abstract ImmutableList<String> getListInstance();
    protected abstract ImmutableList<String> getListInstanceFromValues(String... values);

    @Test
    public void testNewListDefaults() {
        ImmutableList<String> listInstance = getListInstance();
        assertThat(listInstance.size()).isEqualTo(0);
        assertThat(listInstance).hasSize(0);
        assertThat(listInstance).isEmpty();
    }


    @Test
    public void buildListFromValues() {
        ImmutableList<String> listInstance = getListInstanceFromValues("hello", "goodbye");

        assertThat(listInstance.size()).isEqualTo(2);
        assertThat(listInstance).hasSize(2);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("hello", "goodbye");
    }

    @Test
    public void buildListFromJavaCollection() {
        Collection<String> collection = Arrays.asList("one", "two", "three");
        ImmutableList<String> listInstance = ImmutableList.from(collection);

        assertThat(listInstance.size()).isEqualTo(3);
        assertThat(listInstance).hasSize(3);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("one", "two", "three");
    }

    @Test
    public void buildListFromJavaIterator() {

        Iterator<String> iterator = newTestIterator();


        ImmutableList<String> listInstance = ImmutableList.from(iterator);

        assertThat(listInstance.size()).isEqualTo(5);
        assertThat(listInstance).hasSize(5);
        assertThat(listInstance).isNotEmpty();
        assertThat(listInstance).containsExactly("0", "1", "2", "3", "4");
    }

    @Test
    public void testGet() {
        ImmutableList<String> listInstance = getListInstanceFromValues("hello", "goodbye");
        assertThat(listInstance.get(0)).isEqualTo("hello");
        assertThat(listInstance.get(1)).isEqualTo("goodbye");
    }

    @Test
    public void testGetIndexOutOFBounds() {
        final ImmutableList<String> listInstance = getListInstance();
        assertThatThrownBy(() -> listInstance.get(0))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index 0 is out of bounds to the size of this collection(0)")
                .hasNoCause();

        ImmutableList<String> newListInstance = getListInstanceFromValues("value1");

        assertThatThrownBy(() -> newListInstance.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index 1 is out of bounds to the size of this collection(1)");
        assertThatThrownBy(() -> newListInstance.get(-1))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index -1 is out of bounds to the size of this collection(1)");
    }

    @Test
    public void listAllowsMultipleItemsOfTheSameValue() {
        ImmutableList<String> listA = getListInstanceFromValues("value1", "value1", "value2");
        assertThat(listA).hasSize(3);

        ImmutableList<String> listB = getListInstance();
        listB = listB.add("hello").add("hello").add("goodbye");
        assertThat(listB).hasSize(3);
    }

    @Test
    public void removeAllItemsOfSameValueFromCollection() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "b");
        assertThat(collection)
                .hasSize(4);
        collection = collection.remove("b");
        assertThat(collection)
                .hasSize(2)
                .contains("a", "c")
                .doesNotContain("b");
    }

    @Test
    public void removeAllListOfValueswithDuplicates() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll("a", "c");
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void removeAllImmutableCollectionWithDuplicates() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d", "a", "c");
        ImmutableCollection<String> itemsToRemove = getCollectionInstanceFromValues("a", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll(itemsToRemove);
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void removeAllIterableWithDuplicates() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d", "a", "c");
        Iterable<String> itemsToRemove = testIterableOf("a", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll(itemsToRemove);
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void removeAllIteratorWithDuplicates() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d", "a", "c");
        Iterator<String> itemsToRemove = testIteratorOf("a", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll(itemsToRemove);
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void removeAllCollectionWithDuplicates() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d", "a", "c");
        Collection<String> itemsToRemove = Arrays.asList("a", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll(itemsToRemove);
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void toJavaSet(){
        ImmutableCollection<String> immutableList = getListInstanceFromValues("one","one", "two", "three");
        Set<String> javaSet = immutableList.toJavaSet();
        assertThat(immutableList.size()).isEqualTo(4);
        assertThat(javaSet).hasSize(3);
        assertThat(javaSet).containsOnly("one", "two", "three");
    }

    @Test
    public void toJavaList(){
        ImmutableCollection<String> immutableList = getListInstanceFromValues("one","one", "two", "three");
        List<String> javaList = immutableList.toJavaList();
        assertThat(immutableList.size()).isEqualTo(4);
        assertThat(javaList).hasSize(4);
        assertThat(javaList).containsExactly("one", "one", "two", "three");
    }

    @Test
    public void toJavaMap(){
        ImmutableCollection<String> immutableList = getListInstanceFromValues("1","1", "2", "3");
        Map<Integer, String> javaMap = immutableList.transformToMap(i -> Tuple2
            .of(Integer.parseInt(i))
            .and(i));
        assertThat(immutableList.size()).isEqualTo(4);
        assertThat(javaMap).hasSize(3);
        assertThat(javaMap).containsOnly(entry(1, "1"), entry(2, "2"), entry(3,"3"));
    }

}
