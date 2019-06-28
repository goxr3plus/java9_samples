package moduleA.trycatch;

public class TryCatch {

	public static void main(String... args) {

		m1();
		m2();
	}

	private static void m1() {
		try (var mi = new Mi()) {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void m2() {
		var mi = new Mi();
		try (mi) {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}



