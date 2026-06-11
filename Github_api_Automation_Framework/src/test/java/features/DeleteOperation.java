
package features;

import org.testng.Reporter;
import org.testng.annotations.Test;


import Generic.Baseclass;
import Generic.GitEndPoints;
import Generic.TestDataStore;
import Generic.Validations;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DeleteOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Delete Git Repository")

	public void Delete_Git_Repo() throws Exception {

		Baseclass.createTestName("Testcase number TC101", "Testername=brahmendra_jayaraju");

		String reponame = TestDataStore.repoName;
		String owner = TestDataStore.owner;

		Response resp = given().spec(reqSpec).pathParam("owner", owner).pathParam("repo", reponame)
				.delete(GitEndPoints.deleteRepo);

		Validations.validateStatusCode(resp, 204);
		Validations.validateStatusMessage(resp, "No Content");

		Reporter.log("The repo successcefully deleted :" + reponame, true);

	}

}