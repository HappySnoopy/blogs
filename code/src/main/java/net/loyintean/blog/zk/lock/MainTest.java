/**
 * Copyright(c) 2011-2017 by  Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.zk.lock;

import org.junit.Test;

/**
 * @author linjun
 * @since 2017年7月21日
 */
public class MainTest {

    @Test
    public void test() {
        DistributedLock lock = null;
        try {
            lock = new DistributedLock(
                "10.255.33.33:2181,10.255.33.33:2182,10.255.33.33:2183",
                "test");
            lock.lock();

            System.out.println("abcd");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 50; i++) {
            new Thread(new Tester()).start();
        }
    }

    private static class Tester implements Runnable {

        private DistributedLock redisLocker = new DistributedLock(
            "10.255.33.33:2181", "maintest");

        private static int x;
        private static int y;

        /**
         * @author linjun
         * @since 2017年6月16日
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {

            while (true) {

                try {
                    System.out.println("=======");
                    this.redisLocker.lock();

                    Tester.x++;
                    Tester.y++;

                    System.out.println(Thread.currentThread().getName()
                        + " : x = " + Tester.x + "; y = " + Tester.y + "; x-y="
                        + (Tester.x - Tester.y));

                    try {
                        Thread.sleep(100l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (this.redisLocker != null) {
                        this.redisLocker.unlock();
                        //                        this.redisLocker = new DistributedLock(
                        //                            "10.255.33.33:2181", "maintest");
                    }
                }

            }
        }
    }
}
