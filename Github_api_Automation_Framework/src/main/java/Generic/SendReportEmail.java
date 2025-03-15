package Generic;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class SendReportEmail {
	public static String filepath = "/src/test/resources/environment.properties";

	@Test
	public void sendEmail() throws Exception {

		if (WebUtilityKeys.readPropertyFiles(filepath, "Sanity").equals("Yes".trim())) {
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath("./apireports/Gitapireport.html");

			attachment.setDisposition(EmailAttachment.ATTACHMENT);

			attachment.setDescription("Automation sanity Extent Report");
			attachment.setName("Api_Automation_Report.html");

			// Create the email message

			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("sandbox.smtp.mailtrap.io");
			email.setSmtpPort(2525);
			email.setAuthentication("e16f0cac519a9b", "d98fcef674bded");
			email.setStartTLSEnabled(true);

			try {

				email.setFrom("brahmendraapiautomation@yopmail.net");

				email.setSubject("API_Automation_Sanity_ExecutionReport");
				email.setMsg(
						"Hi Team,\n\nplease find attached Sanity automation ExtentReport. Download and open in Chrome Broswer.\n\n\nThanksRegards,\nBrahmendra jayaraju\n+1 (302) 981-6805");

				email.addTo("brahmendra.jayaraju67@gmail.com");
				email.addTo("manu_1995_16@yopmail.net");

			}

			catch (Exception e)

			{

			}

			email.attach(attachment);

			email.send();

		}

		else if (WebUtilityKeys.readPropertyFiles(filepath, "Regression").equals("Yes".trim()))

		{

			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath("./apireports/Gitapireport.html");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Automation Regression Extent Report");
			attachment.setName("Api_Automation_Report.html");

			// Create the email message

			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("sandbox.smtp.mailtrap.io");
			email.setSmtpPort(2525);
			email.setAuthentication("e16f0cac519a9b", "d98fcef674bded");
			email.setStartTLSEnabled(true);

			try {
				email.setFrom("brahmendraapiautomation@yopmail.net");

				email.setSubject("API__Automation_Regression_ExecutionReport");
				email.setMsg(
						"Hi Team,\n\nplease find attached Regression automation ExtentReport. Download and  open in Chrome Broswer.\n\n\nThanksRegards,\nBrahmendra jayaraju\n+1 (302) 981-6805");

				email.addTo("brahmendra.jayaraju67@gmail.com");

				email.addTo("manu_1995_16@yopmail.net");

			}

			catch (Exception e)

			{

				;

			}

			email.attach(attachment);

			email.send();

		}

	}

}
