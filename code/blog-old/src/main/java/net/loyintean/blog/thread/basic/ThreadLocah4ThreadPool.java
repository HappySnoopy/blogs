package net.loyintean.blog.thread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocah4ThreadPool {

	private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();
	private static final InheritableThreadLocal<String> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

	private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

	static class SubThread implements Runnable {

		private String mainLocal;

		public SubThread() {
			mainLocal = LOCAL.get();
		}

		@Override
		public void run() {
			LOCAL.set(mainLocal);
			System.out.println("local in sub : " + LOCAL.get() + "; " + INHERITABLE_THREAD_LOCAL.get());

		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			LOCAL.set("not inheritablet thread local_" + i);
			INHERITABLE_THREAD_LOCAL.set("inheritablet thread local_" + i);
			System.out.println("local in main : " + LOCAL.get() + "; " + INHERITABLE_THREAD_LOCAL.get());
			EXECUTOR_SERVICE.submit(new SubThread());
		}
	}
}
