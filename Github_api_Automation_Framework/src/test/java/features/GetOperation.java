package features;

import org.testng.annotations.Test;

import Generic.WebUtilityKeys;
import Generic.log;
import Generic.Baseclass;
import Generic.GitEndPoints;
import Generic.TestDataStore;
import Generic.Validations;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Get Git Repo Details")
	public void Get_Repo_Info() throws Exception {
		Baseclass.createTestName("Testcase number TC3500", "Testername=brahmendra_jayaraju");

		String owner = WebUtilityKeys.readPropertyFiles(Gitdata, "ownerName");
		String reponame = TestDataStore.repoName;
		int id = TestDataStore.id;

		Response resp = given().spec(reqSpec).pathParam("owner", owner).pathParam("repo", reponame).when()
				.get(GitEndPoints.getRepo);

		resp.then().spec(resSpec);

		log.logAll(resp);

		Validations.validateStatusCode(resp, 200);

		Validations.validateStatusMessage(resp, "OK");

		Validations.validateHeader(resp, "Server", "github.com");

		Validations.validateHeaderContains(resp, "Content-Type", "application/json");

		Validations.validateHeaderExists(resp, "X-RateLimit-Limit");

		Validations.validateHeaderExists(resp, "X-GitHub-Request-Id");

		Validations.validateField(resp, "owner.login", "BrahmendraJayaraju");

		Validations.validateResponseTime(resp, 2000L);

		Validations.validateField(resp, "name", reponame);

		Validations.validateField(resp, "id", id);

		Validations.validateSchema(resp, "schema_get_repo.json");

	}

}
