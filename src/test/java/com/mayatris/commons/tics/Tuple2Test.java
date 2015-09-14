package com.mayatris.commons.tics;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jano on 10/09/15.
 */
public class Tuple2Test {

    @Test
    public void setGet() {

        String param1 = "test";
        Integer param2 = new Integer(12);

        Tuple2<String, Integer> tuple1 = Tuple2.from(param1, param2);
        Tuple2<String, Integer> tuple2 = Tuple2.from(param1, param2);

        assertThat(tuple1.getItem1()).isEqualTo("test");
        assertThat(tuple1.getItem2()).isEqualTo(12);
        assertThat(tuple1).isEqualTo(tuple2);
    }

    @Test
    public void strictTestEquals() {
        EqualsVerifier.forClass(Tuple2.class)
                .verify();
    }

}