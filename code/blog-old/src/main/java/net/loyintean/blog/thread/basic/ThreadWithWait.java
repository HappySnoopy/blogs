package net.loyintean.blog.thread.basic;

public class ThreadWithWait {

	private static Object monitor = new Object();
	private static TrafficLight light = TrafficLight.RED;

	public static class ThreadAsConsumer extends Thread {
		@Override
		public void run() {

			while (true) {
				if (light == TrafficLight.RED) {
					System.out.println("红灯停");
					try {
						synchronized (monitor) {
							monitor.wait();
						}
					} catch (InterruptedException e) {
					}
				} else {
					System.out.println("绿灯行");
				}
			}
		}
	}

	public static class ThreadAsProducer extends Thread {
		@Override
		public void run() {
			while (true) {
				light = TrafficLight.values()[(light.ordinal() + 1) % TrafficLight.values().length];
				try {
					synchronized (monitor) {
						monitor.notifyAll();
					}
				} catch (Exception e) {
				}
				try {
					sleep(1000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new ThreadAsConsumer().start();
		new ThreadAsProducer().start();
	}
}
