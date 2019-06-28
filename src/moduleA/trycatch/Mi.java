package moduleA.trycatch;

public class Mi implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println("Closing!!!!");
	}
}
