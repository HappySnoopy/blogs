package net.loyintean.blog.thread.basic;

public class ThreadLocal4SubThread {

	private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();
	private static final InheritableThreadLocal<String> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

	static class SubThread extends Thread {
		
		@Override
		public void run() {
			System.out.println("local in sub : " + LOCAL.get()+"; "+INHERITABLE_THREAD_LOCAL.get());
		}

	}

	public static void main(String[] args) {
		LOCAL.set("not inheritablet thread local");
		INHERITABLE_THREAD_LOCAL.set("inheritablet thread local");
		System.out.println("local in main : " + LOCAL.get()+"; "+INHERITABLE_THREAD_LOCAL.get());
		new SubThread().start();
	}
}
