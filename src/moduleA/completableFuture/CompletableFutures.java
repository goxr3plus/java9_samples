package moduleA.completableFuture;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutures {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		new CompletableFutures();

		ArrayList<String> ar = new ArrayList<>(){

		};

	}

	private CompletableFutures() throws ExecutionException, InterruptedException {

//		future1();
//		System.err.println("-----");
//		future2();
//		System.err.println("-----");
//		future3();
//		System.err.println("-----");
//		future4();
//		System.err.println("-----");
		future5();
		System.err.println("-----");
	}

	private void future1() throws ExecutionException, InterruptedException {

		java.util.concurrent.CompletableFuture.runAsync(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.err.println("Thread :" + Thread.currentThread().getName());
			System.out.println("Future Diamond 1: runAsync");
		}).thenAccept(value -> {
			System.err.println("Thread :" + Thread.currentThread().getName());
			System.out.println("Future 1 value => " + value);
		}).get();

	}

	private void future2() throws ExecutionException, InterruptedException {

		java.util.concurrent.CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.err.println("Thread :" + Thread.currentThread().getName());
			System.out.println("Future Diamond 2: supplyAsync");
			return "mama";
		}).thenAccept(value -> {
			System.err.println("Thread :" + Thread.currentThread().getName());
			System.out.println("Future 2 value => " + value);
		}).get();

	}

	private void future3() throws ExecutionException, InterruptedException {

		java.util.concurrent.CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.err.println("Thread :" + Thread.currentThread().getName());
			System.out.println("Future Diamond 3: supplyAsync");
			return "mama";
		}).thenAcceptAsync(value -> {
			System.err.println("Thread :" + Thread.currentThread().getName());
			System.out.println("Future 3 value => " + value);
		}).get();

	}

	private void future4() throws ExecutionException, InterruptedException {

		final java.util.concurrent.CompletableFuture f1 = java.util.concurrent.CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.err.println("Thread :" + Thread.currentThread().getName());
			System.out.println("Future Diamond 4.1: supplyAsync");
			return "mama1";
		});

		final java.util.concurrent.CompletableFuture f2 = java.util.concurrent.CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.err.println("Thread :" + Thread.currentThread().getName());

			System.out.println("Future Diamond 4.2: supplyAsync");
			return "mama2";
		});

		f1.thenCombine(f2, (v1, v2) -> "V1 : " + v1 + " , V2: " + v2).thenAcceptAsync(System.out::println).get();
	}

	private void future5() throws ExecutionException, InterruptedException {

		System.err.println("Main Thread : " + Thread.currentThread().getName());

		final java.util.concurrent.CompletableFuture f1 = java.util.concurrent.CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.err.println("Thread :" + Thread.currentThread().getName());
			System.out.println("Future Diamond 5.1: supplyAsync");
			return "mama1";
		});

		final java.util.concurrent.CompletableFuture f2 = java.util.concurrent.CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.err.println("Thread :" + Thread.currentThread().getName());

			System.out.println("Future Diamond 5.2: supplyAsync");
			return "mama2";
		});

		final java.util.concurrent.CompletableFuture f3 = java.util.concurrent.CompletableFuture.supplyAsync(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.err.println("Thread :" + Thread.currentThread().getName());

			System.out.println("Future Diamond 5.3: supplyAsync");
			return "mama2";
		});

		System.out.println("-------Final RESULT------");
		java.util.concurrent.CompletableFuture allFutures = java.util.concurrent.CompletableFuture.allOf(f1, f2, f3);

		// When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -
		java.util.concurrent.CompletableFuture allPageContentsFuture = allFutures.thenApply(v -> {
			return Stream.of(f1, f2, f3)
				.map(java.util.concurrent.CompletableFuture::join)
				.collect(Collectors.toList());
		});

		allPageContentsFuture.thenApply(v ->{
			System.out.println(v);
			return "a";
		}).get();
	}

}



