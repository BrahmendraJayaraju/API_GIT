
package features;

import org.testng.annotations.Test;

import Generic.WebUtilityKeys;
import Generic.log;
import Generic.GitEndPoints;
import Generic.TestDataStore;
import Generic.Validations;
import genericPojo.Project;
import Generic.Baseclass;


import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PatchOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Update Git Repository")
	public void UPDATE_CreateD_Repo() throws Exception {
		Baseclass.createTestName("Testcase number TC3505", "Testername=brahmendra_jayaraju");

		String reponame = TestDataStore.repoName;
		String owner = TestDataStore.owner;
		int id = TestDataStore.id;

		String description = WebUtilityKeys.readPropertyFiles(Gitdata, "updaterepodescription");

		Project p1 = new Project(reponame, description);

		Response resp = given().spec(reqSpec).body(p1).pathParam("owner", owner).pathParam("repo", reponame)
				.patch(GitEndPoints.updateRpo);

		resp.then().spec(resSpec);

		log.logAll(resp);

		Validations.validateStatusCode(resp, 200);
		Validations.validateStatusMessage(resp, "OK");

		Validations.validateHeader(resp, "Server", "github.com");

		Validations.validateHeaderContains(resp, "Content-Type", "application/json");

		Validations.validateHeaderExists(resp, "X-RateLimit-Limit");

		Validations.validateHeaderExists(resp, "X-GitHub-Request-Id");

		Validations.validateField(resp, "owner.login", "BrahmendraJayaraju");

		Validations.validateHeader(resp, "Server", "github.com");

		Validations.validateHeaderContains(resp, "Content-Type", "application/json");

		Validations.validateHeaderExists(resp, "X-RateLimit-Limit");

		Validations.validateHeaderExists(resp, "X-GitHub-Request-Id");

		Validations.validateField(resp, "owner.login", "BrahmendraJayaraju");

		Validations.validateResponseTime(resp, 6000L);
		Validations.validateField(resp, "name", reponame);

		Validations.validateField(resp, "id", id);

		Validations.validateField(resp, "description", description);

		Validations.validateSchema(resp, "schema_patch_repo.json");

	}

}