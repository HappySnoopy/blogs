package net.loyintean.blog.leetcode;

import java.util.concurrent.Semaphore;

/**
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 *
 * @author Snoopy
 * @date 2020 -04-24
 */
public class PrintFoobarAlternately {


    class FooBar {

        private Semaphore semaphoreFoo = new Semaphore(1);
        private Semaphore semaphoreBar = new Semaphore(0);

        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                semaphoreFoo.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                semaphoreBar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                semaphoreBar.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                semaphoreFoo.release();
            }
        }
    }
}
