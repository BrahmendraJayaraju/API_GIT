package Features;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Generic.WebUtilityKeys;
import github_api_automationframework.Baseclass;
import github_api_automationframework.TestListeners;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

@Listeners(TestListeners.class)
public class GetOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Get Git Repo Details")
	public void Get_Repo_Info() throws Exception {
		Baseclass.createTestName("Testcase number TC3500", "Testername=brahmendra_jayaraju");

		given().pathParam("owner", WebUtilityKeys.readPropertyFiles(Gitdata, "ownerName"))
				.pathParam("repo", WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname"))
				.get("/repos/{owner}/{repo}").then().log().all().assertThat().statusCode(200)
				.contentType(ContentType.JSON);

	}


	@Test(priority = 3, groups = { "Regression" }, description = "Fail This TC for just to Show in Report ")
	public void Fail_this_TC() throws Exception {

		Baseclass.createTestName("Testcase number TC9000", "Testername=shashank");
		given().delete(" http://localhost:3000/posts/1070").then().log().all().assertThat().statusCode(200);

	}

	@Test(priority = 4, groups = { "Regression" }, description = "Skip  This TC for just to Show in Report", dependsOnMethods = {
			"Fail_this_TC" })
	public void Skip_TC() throws Exception {

		Baseclass.createTestName("Testcase number TC789", "Testername=jayaraju");
		given().delete("http://localhost:3000/posts/104").then().log().all().assertThat().statusCode(200);

	}

}
