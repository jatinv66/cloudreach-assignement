package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./extent.html");
	htmlReporter.config().setTheme(Theme.DARK);
	extent = new ExtentReports();
	extent.attachReporter(htmlReporter);
	return extent;
    }
}
