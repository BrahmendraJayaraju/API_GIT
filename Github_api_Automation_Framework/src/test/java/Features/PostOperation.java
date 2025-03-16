
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
public class PostOperation extends Baseclass {

	//String name="dummyproject"+GenerateRandomNumber.getRandomNumber();
	String name = "mugimeshi";
	Project p = new Project(name);

	@Test(priority = 0, groups = { "Regression" }, description = "Create  Git Repository")
	public void Update_Repo() throws Exception {
		Baseclass.createTestName("Testcase number TC9886", "Testername=brahmendra_jayaraju");

		Response resp = given().auth().oauth2(WebUtilityKeys.readPropertyFiles(Gitdata, "oathToken"))
				.contentType(ContentType.JSON).body(p).post(GitEndPoints.postoRepo);
		resp.then().log().all().assertThat().statusCode(201).contentType(ContentType.JSON);

		// String gitreproName=resp.jsonPath().get("name");

		Reporter.log("The name of the repository whicis successcefully created ");
		// +gitreproName,true );

	}

}