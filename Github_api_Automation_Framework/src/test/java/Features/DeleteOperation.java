
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
public class DeleteOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Delete Git Repository")
	public void Delete_Git_Repo() throws Exception {
		Baseclass.createTestName("Testcase number TC101", "Testername=brahmendra_jayaraju");

		given().auth().oauth2(WebUtilityKeys.readPropertyFiles(Gitdata, "oathToken")).contentType(ContentType.JSON)
				.pathParam("owner", WebUtilityKeys.readPropertyFiles(Gitdata, "ownerName"))
				.pathParam("repo", WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname"))
				.delete(GitEndPoints.deleteRepo).then().log().all().assertThat().statusCode(204);

	}

}