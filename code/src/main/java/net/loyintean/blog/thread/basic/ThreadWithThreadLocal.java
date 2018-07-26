package net.loyintean.blog.thread.basic;

public class ThreadWithThreadLocal extends Thread {

	private static final ThreadLocal<String> LOCAL_NAME = new ThreadLocal<>();

	@Override
	public void run() {
		while (true) {
			LOCAL_NAME.set(getName());
			// 由于THREADLOCAL是隔离的，这里的两个值一定是匹配的
			System.out.println(getName() + " : " + LOCAL_NAME.get());
			if (!getName().equals(LOCAL_NAME.get())) {
				throw new RuntimeException();
			}
			try {
				sleep(5000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	public static void main(String[] args) {
		new ThreadWithThreadLocal().start();
		new ThreadWithThreadLocal().start();
		new ThreadWithThreadLocal().start();
		new ThreadWithThreadLocal().start();
	}
}
