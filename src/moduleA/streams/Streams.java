package moduleA.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

	private static Predicate<Integer> isEven = i -> i % 2 == 0;

	public static void main(String... args) {

		List<Integer> list = IntStream.rangeClosed(4, 8)
			.boxed().collect(Collectors.toList());

		flatMap(list);
		takeWhile(list);
		dropWhile(list);
		streamIterate(list);
		streamOfNullable(list);

	}

	private static void flatMap(List<Integer> list) {
		System.out.println("--------flatMap--------");

		final List<Integer> collect = list.stream().filter(i -> isEven.test(i)).flatMap(x -> Stream.of(x, x * 10)).collect(Collectors.toList());
		System.out.println(collect);

	}

	private static void takeWhile(List<Integer> list) {
		System.out.println("--------takeWhile--------");

		final List<Integer> collect = list.stream().takeWhile(i -> isEven.test(i)).collect(Collectors.toList());
		System.out.println(collect);
	}

	private static void dropWhile(List<Integer> list) {
		System.out.println("--------dropWhile--------");

		final List<Integer> collect = list.stream().dropWhile(i -> isEven.test(i)).collect(Collectors.toList());
		System.out.println(collect);
	}

	private static void streamIterate(List<Integer> list) {
		System.out.println("--------streamIterate--------");

		/* Java 8 */
		Stream.iterate(1, x -> x * 2).limit(list.size()).forEach(System.out::println);


		/* Java 9 */
		Stream.iterate(1, p -> p < list.size(), p -> p + 1).forEach(System.err::println);

	}

	private static void streamOfNullable(List<Integer> list) {
		System.out.println("--------streamOfNullable--------");

		System.err.println(" LIST ");

		/* List */
		List<String> l = new ArrayList<>(Arrays.asList("a", "b", null, "c", null, "d"));

		l.stream().flatMap(Stream::ofNullable).forEach(System.out::println);

		System.err.println(" MAP ");

		/* Map */
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, null);
		map.put(4, "c");
		map.put(5, null);

		map.entrySet().stream().flatMap(x -> Stream.ofNullable(x.getValue())).forEach(System.out::println);

	}

}



