package listener;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import helpers.ReportMapper;
import org.testng.ITestResult;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ExtentReport extends BaseTest {
    static Path filePath = Paths.get(System.getProperty("user.dir"), "reports", "fileName");
    public static ExtentReports extent = listener.ExtentManager.createInstance(filePath.toString());
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();

    public static void dumpDataForExtentReport(ITestResult result, ReportMapper reportMapper) {

	ExtentTest extentTest;
	String apiCalls = reportMapper.apiCallsName.toString();
	extentTest = extent.createTest(result.getMethod().getMethodName()
		+ "-->" + reportMapper.getTestCaseType()).assignCategory(result.getMethod().getGroups());
	testReport.set(extentTest);

	if (result.getStatus() == ITestResult.SUCCESS) {
	    String passLogg = "TESTCASE PASSED";
	    Markup m = MarkupHelper.createLabel(passLogg, ExtentColor.GREEN);
	    testReport.get().log(Status.PASS, m);
	    String logText = "<details>" + "<summary>" + "<b>" + "<font color=" + "white>" + "API Calls In Order"
		    + ": Click to see" + "</font>" + "</b >" + "</summary>" + apiCalls + "</details>" + " \n";
	    testReport.get().info(logText);
	} else if (result.getStatus() == ITestResult.FAILURE) {
	    String failureLogg = "TEST CASE FAILED";
	    Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
	    testReport.get().log(Status.FAIL, m);
	    testReport.get()
		    .fail("<details>" + "<summary>" + "<b>" + "<font color=" + "white>"
			    + "Exception Occurred: Click to see" + "</font>" + "</b >" + "</summary>"
			    + reportMapper.getTestCaseFailedReason() + "</details>" + " \n");

	    String logText = "<details>" + "<summary>" + "<b>" + "<font color=" + "white>" + "API Calls In Order"
		    + ": Click to see" + "</font>" + "</b >" + "</summary>" + apiCalls + "</details>" + " \n";
	    testReport.get().info(logText);
	} else if (result.getStatus() == ITestResult.SKIP) {
	    String failureLogg = "TEST CASE SKIPPED";
	    Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.YELLOW);
	    testReport.get().log(Status.SKIP, m);
	}

	String splitter = ":::";

	if (reportMapper.getCurlForAPI().contains(splitter)) {

	    for (int i = 1; i < reportMapper.getCurlForAPI().split(splitter).length; i++)
		testReport.get()
			.info("<details>" + "<summary>" + "<b>" + "<font color=" + "white>" + "CURL " + (i)
				+ ": Click to see" + "</font>" + "</b >" + "</summary>"
				+ reportMapper.getCurlForAPI().split(splitter)[i] + "</details>" + " \n");
	} else
	    testReport.get()
		    .info("<details>" + "<summary>" + "<b>" + "<font color=" + "white>" + "CURL " + ": Click to see"
			    + "</font>" + "</b >" + "</summary>" + reportMapper.getCurlForAPI() + "</details>" + " \n");

	if (reportMapper.getApiResponse().contains(splitter)) {
	    for (int i = 1; i < reportMapper.getApiResponse().split(splitter).length; i++)
		testReport.get()
			.info("<details>" + "<summary>" + "<b>" + "<font color=" + "white>" + "API Response " + (i)
				+ ": Click to see" + "</font>" + "</b >" + "</summary>"
				+ reportMapper.getApiResponse().split(splitter)[i] + "</details>" + " \n");
	} else

	    testReport.get()
		    .info("<details>" + "<summary>" + "<b>" + "<font color=" + "white>" + "API Response"
			    + ": Click to see" + "</font>" + "</b >" + "</summary>" + reportMapper.getApiResponse()
			    + "</details>" + " \n");

		if (reportMapper.getGetAPIStatusCode().contains(splitter)) {
			for (int i = 1; i < reportMapper.getGetAPIStatusCode().split(splitter).length; i++)
				testReport.get()
						.info("<details>" + "<summary>" + "<b>" + "<font color=" + "white>" + "API Response Status Code " + (i)
								+ ": Click to see" + "</font>" + "</b >" + "</summary>"
								+ reportMapper.getGetAPIStatusCode().split(splitter)[i] + "</details>" + " \n");
		} else

			testReport.get()
					.info("<details>" + "<summary>" + "<b>" + "<font color=" + "white>" + "API Response Status Code"
							+ ": Click to see" + "</font>" + "</b >" + "</summary>" + reportMapper.getGetAPIStatusCode()
							+ "</details>" + " \n");

	testReport.get()
		.info("<details>" + "<summary>" + "<b>" + "<font color=" + "white>"
			+ "Data Provider Values:Click to see" + "</font>" + "</b >" + "</summary>"
			+ Arrays.toString(result.getParameters()) + "</details>" + " \n");

	testReport.get()
		.info("<details>" + "<summary>" + "<b>" + "<font color=" + "white>"
			+ "TestCase Execution Time(ms) : Click to see" + "</font>" + "</b >" + "</summary>"
			+ reportMapper.getTestCaseExecutionTime() + "</details>" + " \n");

    }
}
