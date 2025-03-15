package github_api_automationframework;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Generic.WebUtilityKeys;

public class Baseclass {

	public static ExtentReports reports;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentTest mainTest;
	public static ExtentTest secondTest;
	public static ExtentTest DeviceTest;

	public static String setUpData = "/src/test/resources/environment.properties";

	public static String Gitdata = "/src/test/resources/data.properties";

	public static void createTestName(String testName, String Authorname) throws Exception {

		String devicename = WebUtilityKeys.readPropertyFiles(setUpData, "Device");

		mainTest = reports.createTest(testName).assignAuthor(Authorname).assignDevice(devicename);

	}

	public static void assignTestCategories(String pageName) {

		// To get category tag names
		mainTest.assignCategory(pageName);

	}

	@BeforeSuite
	public void extentReportSetUp() throws Exception

	{

		String ReportPath = "apireports/Gitapireport.html";

		reports = new ExtentReports();
		htmlReporter = new ExtentSparkReporter(ReportPath);

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("GITHUB_API_TestReport");
		htmlReporter.config().setTimelineEnabled(true);

		reports.attachReporter(htmlReporter);

		if (WebUtilityKeys.readPropertyFiles(setUpData, "TestingScope").equals("SanityTestCases")) {
			htmlReporter.config().setReportName(WebUtilityKeys.readPropertyFiles(setUpData, "SanityReportName"));
			reports.setSystemInfo("No. Of Test cases", WebUtilityKeys.readPropertyFiles(setUpData, "SanityTestCases"));
			reports.setSystemInfo("Testing Scope", WebUtilityKeys.readPropertyFiles(setUpData, "TestingScope"));
		} else if (WebUtilityKeys.readPropertyFiles(setUpData, "TestingScope").equals("RegressionTestCases")) {
			htmlReporter.config().setReportName(WebUtilityKeys.readPropertyFiles(setUpData, "RegressionReportName"));
			reports.setSystemInfo("No. Of Test cases",
					WebUtilityKeys.readPropertyFiles(setUpData, "RegressionTestCases"));
			reports.setSystemInfo("Testing Scope", WebUtilityKeys.readPropertyFiles(setUpData, "TestingScope"));
		} else if (WebUtilityKeys.readPropertyFiles(setUpData, "TestingScope").equals("UserStories")) {
			htmlReporter.config().setReportName(WebUtilityKeys.readPropertyFiles(setUpData, "FunctionalName"));
			reports.setSystemInfo("No. Of Test cases",
					WebUtilityKeys.readPropertyFiles(setUpData, "FuctionalTestcases"));
			reports.setSystemInfo("Testing Scope", WebUtilityKeys.readPropertyFiles(setUpData, "TestingScope"));
		}

		reports.setSystemInfo("TeamName", WebUtilityKeys.readPropertyFiles(setUpData, "TeamName"));
		reports.setSystemInfo("OS", WebUtilityKeys.readPropertyFiles(setUpData, "OS"));
		reports.setSystemInfo("Environment", WebUtilityKeys.readPropertyFiles(setUpData, "Environment"));
		reports.setSystemInfo("Api Type", WebUtilityKeys.readPropertyFiles(setUpData, "apitype"));

		reports.setReportUsesManualConfiguration(false);

	}

	@BeforeMethod
	public void openapp() throws Exception {
		RestAssured.baseURI = WebUtilityKeys.readPropertyFiles(Gitdata, "baseurl");
	}

	@AfterMethod
	public void PostConditionTestStatus(ITestResult result) throws Exception {

		ITestNGMethod m = result.getMethod();
		String name = m.getMethodName();

		String description = result.getMethod().getDescription();

		if (result.getStatus() == ITestResult.SUCCESS) {

			secondTest = mainTest.createNode(description);
			secondTest.pass(MarkupHelper.createLabel(name + " \"Test api method is passed\"", ExtentColor.GREEN));

			Baseclass.assignTestCategories("Feature" + result.getTestClass().getRealClass().getName().substring(13));

		}

		else if (result.getStatus() == ITestResult.FAILURE) {
			secondTest = mainTest.createNode(description);

			Baseclass.assignTestCategories("Feature" + result.getTestClass().getRealClass().getName().substring(13));

			try {

				secondTest.fail(MarkupHelper.createLabel(name + " \"Test api method is Fail\"", ExtentColor.RED));

				// to capture status,name of testmethod and screenshots in Tests node

				// To get Bug icon and exceptions of Defects and info
				secondTest.info(result.getThrowable());

			}

			catch (Exception e) {

				// To get Bug icon and exceptions of Defects and info
				secondTest.log(Status.FAIL, e.getMessage());

				System.out.println("error message" + e.getMessage());

			}

		}

		reports.flush();

	}

	@AfterClass
	public void addToReport() {
		reports.flush();

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

}
