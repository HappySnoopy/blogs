package net.loyintean.blog;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author 林俊（junlin8@creditease.cn）
 * @description
 * @since 2018/7/2/0002 16:50
 */
public class CommonTest {

    @Test
    public void testArrayListIncrease() {
        int max = 10000000;

        for (int times = 0; times <= 5; times++) {
            System.gc();
/*
            3436;497;425;272;74;68;end
*/

            List<Integer> list = new ArrayList<>();
            long start = System.currentTimeMillis();
            IntStream.range(0, max).forEach(i -> list.add(i));
            long end = System.currentTimeMillis();
            System.out.print(end - start+";");

        }
        System.out.println("end");
    }
}
