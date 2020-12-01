package net.loyintean.blog.thread.basic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ThreadByForkJoin {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinTask<Integer> result = ForkJoinPool.commonPool().submit(new FibTask(10));
		int result1 = result.get();
		System.out.println(" result = " + result1);
	}
}

/**
 * 用ForkJoin的方式计算斐波那契数列
 *
 * @author Snoopy
 */
class FibTask extends RecursiveTask<Integer> {

	/**
	 * 要计算的下标
	 */
	private int n = 0;

	/**
	 * 指定数值
	 * 
	 * @param n
	 */
	public FibTask(int n) {
		super();
		this.n = n;
	}

	@Override
	protected Integer compute() {
		int result;
		if (n == 0 || n == 1) {
			result = 1;
		} else if (n == 2) {
			result = 2;
		} else {
			FibTask n_1 = new FibTask(n - 1);
			FibTask n_2 = new FibTask(n - 2);
			n_1.fork();
			n_2.fork();

			int fib_n_1 = n_1.join();
			int fib_n_2 = n_2.join();
			result = fib_n_1 + fib_n_2;
		}
		System.out.println(Thread.currentThread().getName() + " : n=" + n + "; result=" + result);
		return result;
	}
}
