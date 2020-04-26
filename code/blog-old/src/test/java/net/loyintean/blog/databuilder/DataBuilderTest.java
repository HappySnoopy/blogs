package net.loyintean.blog.databuilder;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataBuilderTest {

    @Test
    public void test() {
        ToBuild toBuild = DataBuilder.of(ToBuild.class).build();
        assertNotNull(toBuild.a);
        assertNotNull(toBuild.b);
        assertNotNull(toBuild.c);
        assertNotNull(toBuild.d);
    }


    public enum ToBuidEnum {
        A, B, C
    }

    public static class ToBuild {

        private Integer a;
        private Long b;
        private String c;
        private ToBuidEnum d;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
        }
    }
}