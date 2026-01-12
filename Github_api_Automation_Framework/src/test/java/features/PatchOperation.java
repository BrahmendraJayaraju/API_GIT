
package features;

import org.testng.annotations.Test;

import org.testng.annotations.Listeners;


import Generic.WebUtilityKeys;

import Generic.GitEndPoints;
import genericPojo.Project;
import Generic.Baseclass;
import github_api_automationframework.TestListeners;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;

@Listeners(TestListeners.class)
public class PatchOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Update Git Repository")
	public void UPDATE_CreateD_Repo() throws Exception {
		Baseclass.createTestName("Testcase number TC3505", "Testername=brahmendra_jayaraju");

		
		
		String token = WebUtilityKeys.readPropertyFiles(Gitdata, "oathToken");

	
	 String owner=WebUtilityKeys.readPropertyFiles(Gitdata, "ownerName");

	String reponame=WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname");
		
		String description=WebUtilityKeys.readPropertyFiles(Gitdata, "updaterepodescription");
		
		
		Project p1 = new Project(reponame,description);

		given().auth().oauth2(token).contentType(ContentType.JSON)
				.body(p1).pathParam(	"owner",owner)
				.pathParam("repo",reponame )
				.patch(GitEndPoints.updateRpo).then().log().all().assertThat().statusCode(200)
				.contentType(ContentType.JSON);

	}

}