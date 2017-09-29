/**
 * All Rights Reserved
 */
package net.loyintean.blog.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author linjun
 * @since 2017年9月28日
 */
public class Fibonacci extends RecursiveTask<Integer> {

    /**
     * @author linjun
     * @since 2017年9月28日
     */
    private static final long serialVersionUID = 5166412444799644347L;

    private int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    /**
     * @author linjun
     * @since 2017年9月28日
     * @see java.util.concurrent.RecursiveAction#compute()
     */
    @Override
    protected Integer compute() {

        System.out.println(Thread.currentThread().getName() + " : " + this.n);
        switch (this.n) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                Fibonacci n_1 = new Fibonacci(this.n - 1);
                Fibonacci n_2 = new Fibonacci(this.n - 2);

                n_1.fork();
                n_2.fork();

                return n_1.join() + n_2.join();
        }
    }

    public static void main(String[] args) {

        Fibonacci task = new Fibonacci(10);

        try {
            //            System.out.println(task.fork().get());

            System.out.println(new ForkJoinPool(4).submit(task).get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}