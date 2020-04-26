package net.loyintean.blog.thread.communication;

/**
 * 使用Synchronized关键字来做各种同步
 * 
 * @author linjun
 *
 */
public class CommunicationBySynchornized {

	private Object lock = new Object();

	private static int out_1 = 0;

	/**
	 * 在静态方法上使用synchronized关键字，等于对这个方法所在的类加锁。
	 */
	private static synchronized void out_1() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + ": " + out_1++);
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void synchronizedStaticMethod() {
		new Thread(() -> out_1()).start();
		new Thread(() -> out_1()).start();
		new Thread(() -> out_1()).start();
	}

	/**
	 * 对某个类的Class使用Synchronized关键字，等于对目标类进行加锁。
	 */
	private static void out_2() {
		while (true) {
			synchronized (Thread.class) {
				System.out.println(Thread.currentThread().getName() + ": " + out_1++);
			}
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void synchronizedClass() {
		new Thread(() -> out_2()).start();
		new Thread(() -> out_2()).start();
		new Thread(() -> out_2()).start();
	}

	public synchronized void out_3() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + ": " + out_1++);
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void synchronizedMethod() {
		new Thread(() -> out_3()).start();
		new Thread(() -> out_3()).start();
		new Thread(() -> out_3()).start();
	}

	public  void out_4() {
		while (true) {
			synchronized(lock) {
			System.out.println(Thread.currentThread().getName() + ": " + out_1++);}
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void synchronizedLockObject() {
		new Thread(() -> out_4()).start();
		new Thread(() -> out_4()).start();
		new Thread(() -> out_4()).start();
	}
	
	public static void main(String[] args) {
		// synchronizedStaticMethod();
		// synchronizedClass();
//		new CommunicationBySynchornized().synchronizedMethod();
//		new CommunicationBySynchornized().synchronizedMethod();
		new CommunicationBySynchornized().synchronizedLockObject();
		new CommunicationBySynchornized().synchronizedLockObject();
	}
}
