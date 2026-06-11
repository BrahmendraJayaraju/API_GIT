
package features;

import org.testng.annotations.Test;
import org.testng.Reporter;

import Generic.WebUtilityKeys;
import Generic.log;
import Generic.GitEndPoints;
import Generic.TestDataStore;
import Generic.Validations;
import genericPojo.Project;
import Generic.Baseclass;


import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PostOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Create  Git Repository")
	public void CREATE_Repo() throws Exception {
		Baseclass.createTestName("Testcase number TC9886", "Testername=brahmendra_jayaraju");

		String name = "mugimeshi" + WebUtilityKeys.getRandomNumber();

		Project p = new Project(name);

		Response resp = given().spec(reqSpec).body(p).post(GitEndPoints.postoRepo);

		resp.then().spec(resSpec);

		log.logAll(resp);

		Validations.validateStatusCode(resp, 201);

		Validations.validateStatusMessage(resp, "Created");

		Validations.validateHeader(resp, "Server", "github.com");

		Validations.validateHeaderContains(resp, "Content-Type", "application/json");

		Validations.validateHeaderExists(resp, "X-RateLimit-Used");

		Validations.validateHeaderExists(resp, "X-RateLimit-Resource");

		Validations.validateField(resp, "owner.login", "BrahmendraJayaraju");

		Validations.validateResponseTime(resp, 6000L);

		Validations.validateField(resp, "name", name);

		Validations.getStringAndValidateNotNull(resp, "id");

		Validations.validateSchema(resp, "CreateRepoSchema.json");

		TestDataStore.repoName = resp.then().extract().path("name");

		TestDataStore.id = resp.then().extract().path("id");

		TestDataStore.owner = resp.then().extract().path("owner.login");

		Reporter.log("The name of the repository which is successcefully created  is :" + name, true);

	}

}