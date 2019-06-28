package moduleA.diamond9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Diamond {

	public static void main(String[] args) {

		ArrayList<String> l = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
		ArrayList<String> l2 = new ArrayList<>() {

		};


		Map<String,Integer> l3 =  new HashMap<>();

		System.out.println(l.getClass());
		System.out.println(l2.getClass());

	}
}
