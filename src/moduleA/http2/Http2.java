package moduleA.http2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

public class Http2 {

	public Http2() throws ExecutionException, InterruptedException {

		http2ClientAsync();
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		new Http2();
	}

	public void http2ClientAsync() {

		//Create http  client
		HttpClient client = HttpClient.newHttpClient();

		//Create http request
		HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.YOUTUBE.com")).GET().build();

		System.out.println();

		//Get the response
		try {
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenAccept(response -> {
					System.err.println("\n-------------STATUS CODE-----------\n");
					System.out.println(response.statusCode());

					System.err.println("\n-------------HEADERS-----------\n");
					response.headers().map().forEach((k, v) -> System.out.println(k + "=" + v));

					System.err.println("\n-------------BODY-----------\n");
//					System.out.println(response.body());
				}).get();

			//Get the response
			client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(Paths.get("b.html"))).get();

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("\n " + request.uri());
	}

}



