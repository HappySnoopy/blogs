package net.loyintean.blog.thread.basic;

import java.util.ArrayList;
import java.util.List;

public class ThreadByRunnable {

	private static final int POOL_SIZE = 10;
	private static final List<String> POOL = new ArrayList<>(POOL_SIZE);
	class IsolationRunnable implements Runnable {
		private int num;

		public IsolationRunnable(int n) {
			this.num = n;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " : " + num);
		}
	}
	static class WaterInRunnable implements Runnable {
		@Override
		public void run() {
			while (true) {
				synchronized (POOL) {
					System.out.println(POOL);
					if (POOL.size() < POOL_SIZE) {
						POOL.add("*");
						POOL.add("*");
						POOL.notify();
					} else {
						try {
							POOL.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	static class WaterOutRunnable implements Runnable {
		@Override
		public void run() {

			while (true) {
				synchronized (POOL) {
					System.out.println(POOL);
					if (POOL.size() > 0) {
						POOL.remove(0);
						POOL.notify();
					} else {
						try {
							POOL.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		public static void main(String[] args) {
			for (int i = 0; i < 3; i++) {
				new Thread(new ThreadByRunnable().new IsolationRunnable(i)).start();
			}

			new Thread(new WaterInRunnable()).start();
			new Thread(new WaterOutRunnable()).start();
			new Thread(new WaterOutRunnable()).start();
		}

	}
}
