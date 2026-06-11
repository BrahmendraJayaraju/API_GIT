package Generic;

import io.restassured.response.Response;

public class log {

	public static void logAll(Response resp) {
		resp.then().log().all();

	}

	public static void logHeaders(Response resp) {
		resp.then().log().headers();
	}

	public static void logBody(Response resp) {
		resp.then().log().body();
	}

	public static void logStatus(Response resp) {
		resp.then().log().status();
	}

}
