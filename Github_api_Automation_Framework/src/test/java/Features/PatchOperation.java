
package Features;

import org.testng.annotations.Test;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Generic.WebUtilityKeys;
import Generic.GenerateRandomNumber;
import Generic.GitEndPoints;
import genericPojo.Project;
import github_api_automationframework.Baseclass;
import github_api_automationframework.TestListeners;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

@Listeners(TestListeners.class)
public class PatchOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Update Git Repository")
	public void Create_Repo() throws Exception {
		Baseclass.createTestName("Testcase number TC3505", "Testername=brahmendra_jayaraju");

		Project p1 = new Project(WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname"),
				WebUtilityKeys.readPropertyFiles(Gitdata, "updaterepodescription"));

		given().auth().oauth2(WebUtilityKeys.readPropertyFiles(Gitdata, "oathToken")).contentType(ContentType.JSON)
				.body(p1).pathParam("owner", WebUtilityKeys.readPropertyFiles(Gitdata, "ownerName"))
				.pathParam("repo", WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname"))
				.patch(GitEndPoints.updateRpo).then().log().all().assertThat().statusCode(200)
				.contentType(ContentType.JSON);

	}

}