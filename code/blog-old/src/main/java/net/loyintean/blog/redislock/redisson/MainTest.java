/**
 * Copyright(c) 2011-2017 by  Inc.(){}
 * All Rights Reserved
 */
package net.loyintean.blog.redislock.redisson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 测试类
 * TODO Snoopy 2017-06-16
 *
 * @author Snoopy
 * @since 2017年6月16日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedicConfig.class)
public class MainTest {

    @Resource
    private RedisLocker redisLocker;

    @Test
    public void contextLoads() throws InterruptedException {

        Runnable r = new Tester(this.redisLocker);

        for (int i = 0; i < 50; i++) {
            new Thread(r).start();
        }

        Thread.sleep(100000000);
    }

    private static class Tester implements Runnable {

        private RedisLocker redisLocker;

        /**
         * @param redisLocker
         */
        public Tester(RedisLocker redisLocker) {
            super();
            this.redisLocker = redisLocker;
        }

        private int x;
        private int y;

        /**
         * @author Snoopy
         * @see java.lang.Runnable#run()
         * @since 2017年6月16日
         */
        @Override
        public void run() {

            while (true) {

                try {
                    System.out.println("=======");
                    this.redisLocker.lock("thread_test", () -> {
                        Tester.this.x++;
                        Tester.this.y++;

                        System.out.println(
                            Thread.currentThread().getName() + " : x = "
                                + Tester.this.x + "; y = " + Tester.this.y
                                + "; x-y=" + (Tester.this.x - Tester.this.y));

                        try {
                            Thread.sleep(100l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
