package net.loyintean.blog.leetcode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

/**
 * https://leetcode-cn.com/problems/print-zero-even-odd/
 *
 * @author Snoopy
 * @date 2020 -04-24
 */
public class PrintZeroEvenOdd {

    public static void main(String[] args) {
        PrintZeroEvenOdd test = new PrintZeroEvenOdd();
        ZeroEvenOdd zeo = test.new ZeroEvenOdd(5);

        new Thread(() -> {
            try {
                zeo.zero(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                zeo.odd(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                zeo.even(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * The type ZeroEvenOdd.
     *
     * @author Snoopy
     * @date 2020 -04-24
     */
    private class ZeroEvenOdd {


        /** The N. */
        private int n;

        private int count = 0;
        private Semaphore[] semaphores = {new Semaphore(0), new Semaphore(0), new Semaphore(1)};

        /**
         * Instantiates a new Zero even odd.
         *
         * @param n the n
         */
        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        /**
         * 只打印0
         *
         * @param printNumber the print number
         * @throws InterruptedException the interrupted exception
         */
        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {

            while (count < n) {
                semaphores[2].acquire();
                printNumber.accept(0);
                semaphores[count++ % 2].release();
            }

            System.out.println("zero end");

        }

        /**
         * 只打印奇数
         *
         * @param printNumber the print number
         * @throws InterruptedException the interrupted exception
         */
        public void odd(IntConsumer printNumber) throws InterruptedException {

            while (count < n) {
                semaphores[1].tryAcquire(1, TimeUnit.SECONDS);
                printNumber.accept(count);
                semaphores[2].release();
            }
            System.out.println("odd end");
        }

        /**
         * 只打印偶数
         *
         * @param printNumber the print number
         * @throws InterruptedException the interrupted exception
         */
        public void even(IntConsumer printNumber) throws InterruptedException {

            while (count < n) {
                semaphores[0].tryAcquire(1, TimeUnit.SECONDS);
                printNumber.accept(count);
                semaphores[2].release();
            }
            System.out.println("even end");
        }

    }
}
