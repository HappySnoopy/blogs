package net.loyintean.exectime.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ExecTimeUtilsTest {

    @Test
    public void test() {

        ExecTimeUtils.ExecTimeResult<Integer> timeResult = ExecTimeUtils.execTime(() -> {

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        System.out.println(timeResult);

        assertEquals(1, (int) timeResult.getResult());
        assertTrue(timeResult.getTime() >= 100L);
    }

    @Test
    public void testLoop() {

        ExecTimeUtils.ExecTimeResult<List<Integer>> timeResult = ExecTimeUtils.execTime(() -> {

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, 10L);

        System.out.println(timeResult);
        timeResult.getResult().forEach(r -> assertEquals(1, (int) r));
        assertTrue(timeResult.getTime() >= 1000L);
    }
}