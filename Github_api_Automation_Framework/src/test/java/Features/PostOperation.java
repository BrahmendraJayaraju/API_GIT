


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
public class PostOperation extends Baseclass {
	
	//String  name="dummyproject"+generaterandom.getRandomNumber();
	String name="mugimeshi";
	project p=new project(name);
	
	

	@Test(priority = 0, groups = { "Regression" }, description = "create  repo mugimeshi")
	public void CreateRepoInfo() throws Exception {
		Baseclass.createTestName("Testcase number TC3501", "Testername=brahmendra_jayaraju");

		
		
		
		 Response resp=given().auth().oauth2(WebUtilityKeys.readPropertyFiles(Gitdata,"oathToken"))
		.contentType(ContentType.JSON)
		.body(p)
		.post(gitendpoints.postoRepo);
		 resp.then().log().all()
		.assertThat().statusCode(201)
		.contentType(ContentType.JSON);
		 
		 //String gitreproName=resp.jsonPath().get("name");
		 
		 
		 //Reporter.log("The name of the repository whicis successcefully created " +gitreproName,true );
		

	}
	
}