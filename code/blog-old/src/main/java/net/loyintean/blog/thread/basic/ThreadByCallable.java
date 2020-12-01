package net.loyintean.blog.thread.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 用{@link Callable}实现多线程
 * <p>
 * 与Runnable相比，Callable提供了一个有返回值的方法。<br>
 * 借此可以简化主线程与子线程之间的通信：主线程可以更方便的获取到子线程的执行结果，而不必繁琐的wait/notify或各类同步数据容器。<br>
 * 不过，Callable也还是需要借助Future/FutureTask等接口/类，来真正的“返回”自己的处理结果。<br>
 *
 * <p>
 * {@link FutureTask}的代码很值得一看。这个类很好的诠释了组合（其中组合了一个Callable）和继承（当然在这里是子接口继承父接口、类实现子接口）。<br>
 * 组合与继承，这是面向设计中的基本思路。
 *
 * @author Snoopy
 */
public class ThreadByCallable implements Callable<String> {

	/*
	 * 假装这个操作很耗时，经过若干耗时操作后，返回"success"
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public String call() throws Exception {
		long start = System.currentTimeMillis();

		// 假装很耗时
		Thread.sleep(5000l);

		long end = System.currentTimeMillis();
		System.out.println("the callable cost " + (end - start) + "ms");
		return "success";
	}

	/**
	 * 展示如何利用FutureTask来串联起Callable/Future和Thread的
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		long start = System.currentTimeMillis();

		// 用FutureTask封装一个Callable。
		// Callable定义如何执行操作、获取返回值
		// FutureTask提供两项能力，后面见分晓。
		FutureTask<String> task = new FutureTask<>(new ThreadByCallable());

		// 第一项能力：多线程执行的能力。FutureTask实现了Runnable接口，可以提交给Thread进行异步执行。
		new Thread(task).start();

		// 这可以执行一些其他的耗时逻辑
		// 假装很耗时
		Thread.sleep(8000l);

		// 第二项能力：Future#get的能力。在启动子线程之后某个“未来”时间点上，获取Callable的返回值。
		String result = task.get();
		long end = System.currentTimeMillis();

		System.out.println("result is : " + result + ", the task cost " + (end - start) + "ms");

		System.exit(0);

	}
}
