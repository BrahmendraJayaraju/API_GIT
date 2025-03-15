
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
public class patchOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "patho mugimeshi")
	public void CreateRepoInfo() throws Exception {
		Baseclass.createTestName("Testcase number TC3505", "Testername=brahmendra_jayaraju");

		project p1 = new project(WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname"),
				WebUtilityKeys.readPropertyFiles(Gitdata, "updaterepodescription"));

		given().auth().oauth2(WebUtilityKeys.readPropertyFiles(Gitdata, "oathToken")).contentType(ContentType.JSON)
				.body(p1).pathParam("owner", WebUtilityKeys.readPropertyFiles(Gitdata, "ownerName"))
				.pathParam("repo", WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname"))
				.patch(gitendpoints.updateRpo).then().log().all().assertThat().statusCode(200)
				.contentType(ContentType.JSON);

	}

}