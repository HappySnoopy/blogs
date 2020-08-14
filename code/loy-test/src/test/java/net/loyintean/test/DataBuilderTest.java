package net.loyintean.test;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataBuilderTest {

    @Test
    public void test() {
        TestData date = DataBuilder.of(TestData.class).build();
        assertNotNull(date.str);
        assertNotNull(date.i);
        assertNotNull(date.l);
        assertNotNull(date.b);

    }


    public static class TestData {
        private String str;
        private Integer i;
        private Long l;
        private Boolean b;
    }
}