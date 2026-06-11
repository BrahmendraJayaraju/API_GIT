package Generic;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.Assert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Validations {

	// Status Code Validation
	public static void validateStatusCode(Response resp, int expectedStatusCode) {

		Assert.assertEquals(resp.getStatusCode(), expectedStatusCode, "Status Code Validation Failed");
	}

	// Status Message Validation
	public static void validateStatusMessage(Response resp, String expectedMessage) {

		Assert.assertTrue(resp.getStatusLine().contains(expectedMessage), "Status Message Validation Failed");
	}

	// Content Type Validation
	public static void validateContentType(Response resp) {

		Assert.assertTrue(resp.getContentType().contains(ContentType.JSON.toString()),
				"Content Type Validation Failed");
	}

	// Response Time Validation
	public static void validateResponseTime(Response resp, long expectedTime) {

		Assert.assertTrue(resp.getTime() < expectedTime,
				"Response Time Validation Failed. Actual Time : " + resp.getTime());
	}

	// Validate Field Value
	public static void validateField(Response resp, String jsonPath, Object expectedValue) {

		Assert.assertEquals(resp.jsonPath().get(jsonPath), expectedValue, "Field Validation Failed : " + jsonPath);
	}

	// Validate Field Exists
	public static void validateFieldExists(Response resp, String jsonPath) {

		Assert.assertNotNull(resp.jsonPath().get(jsonPath), "Field Not Found : " + jsonPath);
	}

	// Validate Array Size
	public static void validateArraySize(Response resp, String jsonPath, int expectedSize) {

		Assert.assertEquals(resp.jsonPath().getList(jsonPath).size(), expectedSize, "Array Size Validation Failed");
	}

	// Validate Array Contains Item
	public static void validateArrayContains(Response resp, String jsonPath, Object expectedValue) {

		Assert.assertTrue(resp.jsonPath().getList(jsonPath).contains(expectedValue),
				"Array Does Not Contain : " + expectedValue);
	}

	// Validate Response Contains String
	public static void validateResponseContains(Response resp, String expectedText) {

		Assert.assertTrue(resp.getBody().asString().contains(expectedText),
				"Response Does Not Contain : " + expectedText);
	}

	public static String getStringAndValidateNotNull(Response resp, String jsonPath) {
		String value = resp.jsonPath().getString(jsonPath);

		Assert.assertNotNull(value, "Value is null for field : " + jsonPath);

		return value;
	}

	// Validate Header

	public static void validateHeader(Response resp, String headerName, String expectedValue) {

		Assert.assertEquals(resp.getHeader(headerName), expectedValue, "Header validation failed: " + headerName);
	}

	public static void validateHeaderContains(Response resp, String headerName, String expectedValue) {

		Assert.assertTrue(resp.getHeader(headerName).contains(expectedValue),
				"Header does not contain expected value: " + headerName);
	}

	public static void validateHeaderExists(Response resp, String headerName) {

		Assert.assertNotNull(resp.getHeader(headerName), "Header not found: " + headerName);
	}

	public static void validateSchema(Response response, String schemaFile) {

		response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaFile));

	}

}
