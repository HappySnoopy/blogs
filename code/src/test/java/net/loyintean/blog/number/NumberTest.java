package net.loyintean.blog.number;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class NumberTest {

    @Test
    public void test() {

        double diff = 7.500 - 7.500;

        assertFalse(diff > 0);
        // result 0.0
        System.out.println(diff);
        if (diff > 0) {
            //result +0.0
            System.out.println("+" + diff);
        } else {
            //result -0.0
            System.out.println("-" + diff);
        }

    }
}
