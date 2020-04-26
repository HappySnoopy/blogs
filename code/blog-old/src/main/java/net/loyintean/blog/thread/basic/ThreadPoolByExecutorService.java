package net.loyintean.blog.thread.basic;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadPoolByExecutorService {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		// Executors.newFixedThreadPool(4);
		// Executors.newSingleThreadExecutor();

		List<Callable<Long>> callables = IntStream.range(0, 5).mapToObj(i -> new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				Thread.sleep(100l * (i + 1));
				return System.currentTimeMillis();
			}
		}).collect(Collectors.toList());

		List<Future<Long>> results = executorService.invokeAll(callables);

		results.forEach(f -> {
			try {
				System.out.println(f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});

		System.exit(0);
	}
}
