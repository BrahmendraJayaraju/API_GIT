
package Features;

import org.testng.annotations.Test;

import org.testng.annotations.Listeners;


import Generic.WebUtilityKeys;
import Generic.Baseclass;
import Generic.GitEndPoints;


import github_api_automationframework.TestListeners;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;

@Listeners(TestListeners.class)
public class DeleteOperation extends Baseclass {

	@Test(priority = 0, groups = { "Regression" }, description = "Delete Git Repository")
	
	public void Delete_Git_Repo() throws Exception {
		
		Baseclass.createTestName("Testcase number TC101", "Testername=brahmendra_jayaraju");
		
		String token = WebUtilityKeys.readPropertyFiles(Gitdata, "oathToken");


		
		//removed

		
		String ownername=WebUtilityKeys.readPropertyFiles(Gitdata, "ownerName");
		String reponame=WebUtilityKeys.readPropertyFiles(Gitdata, "repositoryname");

		given().auth().oauth2(token).contentType(ContentType.JSON)
				.pathParam("owner",ownername )
				.pathParam("repo",reponame )
				.delete(GitEndPoints.deleteRepo).then().log().all().assertThat().statusCode(204);

	}

}