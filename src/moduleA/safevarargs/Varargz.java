package moduleA.safevarargs;

import java.util.Arrays;
import java.util.List;

public class Varargz {

	public static void main(String[] args) {

		List<String> l1 = Arrays.asList("A", "B");
		List<String> l2 = Arrays.asList("C", "D");

		m1(l1, l2);
		m2(l1, l2);

	}

	@SafeVarargs
	private static void m1(List<String>... l) { //argument will become List<String>[]

		for (var l2 : l) {
			System.out.println(l2);
		}
	}

	private static void m2(List<String>... l) { //argument will become List<String>[]

		Object[] a = l;
		a[0] = Arrays.asList(10, 20);
		String name = l[0].get(0); //10
		System.out.println(name);
	}

}
