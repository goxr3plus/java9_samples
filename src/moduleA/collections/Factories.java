package moduleA.collections;

import java.util.Map;

public class Factories {

	public Factories() {


		/* Μap */
		Map<String, String> map = Map.ofEntries(Map.entry("A", "Apple"),Map.entry("B","BOSS"));

		System.out.println(map);
	}

	public static void main(String[] args) {
		new Factories();
	}
}
