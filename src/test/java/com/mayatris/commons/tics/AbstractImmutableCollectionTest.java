package com.mayatris.commons.tics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    public void addAllNullParamsThrowsException() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("1", "2");
        assertThatThrownBy(() -> collection.addAll((ImmutableCollection<String>)null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

        assertThatThrownBy(() -> collection.addAll((Collection<String>) null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

        assertThatThrownBy(() -> collection.addAll((Iterable<String>)null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

        assertThatThrownBy(() -> collection.addAll((Iterator<String>)null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

        assertThatThrownBy(() -> collection.addAll((String[])null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");
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
        final Collection<String> other = Arrays.asList("b value 1", "b value 2");

        ImmutableCollection<String> mergedCollection = collectionInstance.addAll(other);
        assertThat(mergedCollection)
            .hasSize(4)
            .containsExactly("a value 1", "a value 2", "b value 1", "b value 2");
    }

    @Test
    public void addAllIterable() {
        final ImmutableCollection<String> collectionInstance = getCollectionInstanceFromValues("a value 1", "a value 2");
        final Iterable<String> other = Arrays.asList("b value 1", "b value 2");

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


    @Test
    public void removeThrowsException() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("1", "2");
        assertThatThrownBy(() -> collection.remove(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void removeItemFromCollection() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c");
        collection = collection.remove("b");
        assertThat(collection)
                .hasSize(2)
                .contains("a", "c")
                .doesNotContain("b");
    }

    @Test
    public void removeItemThatDoesntExistFromCollection() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c");
        collection = collection.remove("d");
        assertThat(collection)
                .hasSize(3)
                .contains("a", "b", "c");
    }


    @Test
    public void removeAllNullsThrowsException() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c");

        assertThatThrownBy(() -> collection.removeAll((String[]) null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

        assertThatThrownBy(() -> collection.removeAll((Iterator<String>) null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

        assertThatThrownBy(() -> collection.removeAll((Iterable<String>) null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

        assertThatThrownBy(() -> collection.removeAll((Collection<String>) null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

        assertThatThrownBy(() -> collection.removeAll((ImmutableCollection<String>) null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("Paramter items, cannot be null");

    }

    @Test
    public void removeAllListOfValues() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d");
        ImmutableCollection<String> mergedCollection = collection.removeAll("a", "c");
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void removeAllImmutableCollection() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d");
        ImmutableCollection<String> itemsToRemove = getCollectionInstanceFromValues("a", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll(itemsToRemove);
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void removeAllIterable() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d");
        Iterable<String> itemsToRemove = testIterableOf("a", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll(itemsToRemove);
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void removeAllIterator() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d");
        Iterator<String> itemsToRemove = testIteratorOf("a", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll(itemsToRemove);
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
    }

    @Test
    public void removeAllCollection() {
        ImmutableCollection<String> collection = getCollectionInstanceFromValues("a", "b", "c", "d");
        Collection<String> itemsToRemove = Arrays.asList("a", "c");
        ImmutableCollection<String> mergedCollection = collection.removeAll(itemsToRemove);
        assertThat(mergedCollection)
            .hasSize(2)
            .containsExactly("b", "d");
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

    protected Iterable<String> testIterableOf(final String... items) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return testIteratorOf(items);
            }
        };
    }

    protected Iterator<String> testIteratorOf(final String... items) {
        return new Iterator<String>() {

            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < items.length;
            }

            @Override
            public String next() {
                return items[count++];
            }
        };
    }
}
