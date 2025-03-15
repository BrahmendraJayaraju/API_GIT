
package Features;

import org.testng.annotations.Test;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Generic.WebUtilityKeys;
import Generic.generaterandom;
import Generic.gitendpoints;
import genericPojo.project;
import github_api_automationframework.Baseclass;
import github_api_automationframework.TestListeners;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

@Listeners(TestListeners.class)
public class DeleteOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "deletemugimeshi")
	public void DeleteRepo() throws Exception {
		Baseclass.createTestName("Testcase number TC3590905", "Testername=brahmendra_jayaraju");

	

		given().auth().oauth2(WebUtilityKeys.readPropertyFiles(Gitdata, "oathToken")).contentType(ContentType.JSON)
				.pathParam("owner", WebUtilityKeys.readPropertyFiles(Gitdata, "ownerName"))
				.pathParam("repo", WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname"))
				.delete(gitendpoints.deleteRepo).
				then().log().all().assertThat().statusCode(204)
				;

	}

}