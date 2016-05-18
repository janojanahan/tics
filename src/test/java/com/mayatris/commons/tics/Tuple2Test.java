package com.mayatris.commons.tics;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tuple2Test {

    @Test
    public void setGet() {

        String param1 = "test";
        Integer param2 = 12;

        Tuple2<String, Integer> tuple1 = Tuple2.from(param1, param2);
        Tuple2<String, Integer> tuple2 = Tuple2.from(param1, param2);

        assertThat(tuple1.one()).isEqualTo("test");
        assertThat(tuple1.two()).isEqualTo(12);
        assertThat(tuple1).isEqualTo(tuple2);
    }

    @Test
    public void strictTestEquals() {
        EqualsVerifier.forClass(Tuple2.class)
                .verify();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void fluentCreate() {
        Tuple2<String, Boolean> tuple = Tuple2.of("one").and(true);
        assertThat(tuple.one()).isEqualTo("one");
        assertThat(tuple.two()).isEqualTo(Boolean.TRUE);
    }

}