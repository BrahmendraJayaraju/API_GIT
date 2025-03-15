package github_api_automationframework;

import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Generic.WebUtilityKeys;

public class TestListeners extends Baseclass implements ITestListener

{

	@Override
	public void onTestSkipped(ITestResult result) {

		String description = result.getMethod().getDescription();

		ITestNGMethod m = result.getMethod();
		String name = m.getMethodName();

		if (result.getStatus() == ITestResult.SKIP) {

			try {

				String devicename = WebUtilityKeys.readPropertyFiles(setUpData, "Device");

				mainTest = reports
						.createTest(result.getTestClass().getRealClass().getName().substring(13) + "_Feature_"
								+ result.getName() + " \"Test API method skipped\"")
						.assignAuthor("Testername=Brahmendra@gmail.com,Dhoni@gmail.com,manu@gmail.com")
						.assignDevice(devicename);

				secondTest = mainTest.createNode(description);

				secondTest.skip(MarkupHelper.createLabel(name + " \"Test api method is Skipped\"", ExtentColor.ORANGE));

				secondTest.info(result.getThrowable());

				mainTest.assignCategory("Feature" + result.getTestClass().getRealClass().getName().substring(13));

			}

			catch (Exception e) {

				secondTest.log(Status.SKIP, e.getMessage());
				System.out.println("error message" + e.getMessage());

			}

		}

		reports.flush();

	}

}
