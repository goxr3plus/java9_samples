package moduleA.process_api;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

public class ProcessApi {

	public static void main(String... args) throws InterruptedException, IOException, ExecutionException {


		/*  */

		/* Get ProcessHandle object From Current running process */
		ProcessHandle handle = ProcessHandle.current();
		showInfo(handle);

		/* Display all info about all descendants*/
		System.err.println("-------------ALL CHILDREN-----------");
		handle.children()
			.sorted(Comparator.comparing(
				ProcessHandle::pid,
				Long::compareTo
			))
			.forEach(ProcessApi::showInfo);


		/* Display all info about all system running processes */
		System.err.println("-------------ALL PROCESSES-----------");
		ProcessHandle.allProcesses()
			.sorted(Comparator.comparing(
				ProcessHandle::pid,
				Long::compareTo
			))
			.forEach(ProcessApi::showInfo);


		/* Destroy one process with given id ( if present ) */
		ProcessHandle.of(22532).ifPresent(ProcessHandle::destroyForcibly);

		/*  ProcessBuilder */
		ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe", "courses.txt");
		final Process process = processBuilder.start();

		Thread.sleep(2000);

		process.onExit().thenRunAsync(() -> System.err.println("MAOUUUUUUUUUUUUUUUUUUUUUUUU")).get();

	}

	private static void showInfo(ProcessHandle handle) {
		System.out.println("--------------------------------------------");
		System.out.println(handle.pid());

		ProcessHandle.Info info = handle.info();
		info.user().ifPresent(System.out::println);
		info.command().ifPresent(System.out::println);
		info.startInstant().ifPresent(System.out::println);
		info.totalCpuDuration().ifPresent(System.out::println);
		info.commandLine().ifPresent(System.out::println);
		info.arguments().ifPresent(System.out::println);
	}
}


