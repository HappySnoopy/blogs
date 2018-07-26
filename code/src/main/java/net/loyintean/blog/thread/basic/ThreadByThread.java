package net.loyintean.blog.thread.basic;

public class ThreadByThread extends Thread {

	@Override
	public void run() {
		while (true) {
			System.out.println(this.getName());
			try {
				Thread.sleep(1000l);
			} catch (Exception e) {
			}

		}
	}

	public static void main(String[] args) throws Exception {
		new ThreadByThread().start();
		new ThreadByThread().start();
		new ThreadByThread().start();
		new ThreadByThread().start();

		Thread.sleep(100000l);
	}
}