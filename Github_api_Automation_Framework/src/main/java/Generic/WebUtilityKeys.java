package Generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WebUtilityKeys {

	public static String readPropertyFiles(String filePath, String locatorName) throws Exception {
		Properties properties = new Properties();

		try {
			String projectpath = System.getProperty("user.dir");

			FileInputStream fileInputStream = new FileInputStream(projectpath + filePath);
			properties.load(fileInputStream);

		} catch (Exception var4) {
			var4.getStackTrace();
		}
		return properties.getProperty(locatorName);
	}

}
