
package features;

import org.testng.annotations.Test;
import org.testng.Reporter;
import org.testng.annotations.Listeners;


import Generic.WebUtilityKeys;

import Generic.GitEndPoints;
import genericPojo.Project;
import Generic.Baseclass;
import github_api_automationframework.TestListeners;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

@Listeners(TestListeners.class)
public class PostOperation extends Baseclass {

	//String name="mugimeshi"+WebUtilityKeys.getRandomNumber();
	String name = "mugimeshi";
	Project p = new Project(name);

	@Test(priority = 0, groups = { "Regression" }, description = "Create  Git Repository")
	public void CREATE_Repo() throws Exception {
		Baseclass.createTestName("Testcase number TC9886", "Testername=brahmendra_jayaraju");

		
		String token = WebUtilityKeys.readPropertyFiles(Gitdata, "oathToken");

		
		Response resp = given().auth().oauth2(token)
				.contentType(ContentType.JSON).body(p).post(GitEndPoints.postoRepo);
		
		
		resp.then().log().all().assertThat().statusCode(201).contentType(ContentType.JSON);

		
		
		
		String gitreproName=resp.jsonPath().get("name");

		Reporter.log("The name of the repository whic is successcefully created  is "+gitreproName,true );

	}

}