package net.loyintean.leetcode;

import java.util.concurrent.CountDownLatch;

/**
 * https://leetcode-cn.com/problems/print-in-order/
 *
 * @author 林俊 <junlin8@creditease.cn>
 * @date 2020 -04-24
 */
public class PrintInOrder {


    /**
     * The type Foo.
     *
     * @author 林俊 <junlin8@creditease.cn>
     * @date 2020 -04-24
     */
    private class Foo {

        /** The Countdown latch 1. */
        private CountDownLatch countdownLatch1 = new CountDownLatch(1);

        /** The Countdown latch 2. */
        private CountDownLatch countdownLatch2 = new CountDownLatch(1);

        /**
         * Instantiates a new Foo.
         */
        public Foo() {

        }

        /**
         * First.
         *
         * @param printFirst the print first
         * @throws InterruptedException the interrupted exception
         */
        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            countdownLatch1.countDown();
        }

        /**
         * Second.
         *
         * @param printSecond the print second
         * @throws InterruptedException the interrupted exception
         */
        public void second(Runnable printSecond) throws InterruptedException {
            countdownLatch1.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            countdownLatch2.countDown();
        }

        /**
         * Third.
         *
         * @param printThird the print third
         * @throws InterruptedException the interrupted exception
         */
        public void third(Runnable printThird) throws InterruptedException {
            countdownLatch2.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
