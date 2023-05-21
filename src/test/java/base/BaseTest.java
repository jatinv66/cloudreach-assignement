package base;

import helpers.ReportMapper;
import listener.ExtentReport;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

public class BaseTest  {
	protected static LinkedHashMap<String, ReportMapper> finalReportData = new LinkedHashMap<>();
	protected static ReportMapper reportMapper;


    @BeforeMethod(alwaysRun = true)
    public static void setReportMapperBeforeTestCase() {
        reportMapper = new ReportMapper();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestCaseExecution(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS)
            reportMapper.setTestCaseStatus("PASS");
        else if (result.getStatus() == ITestResult.FAILURE) {
            reportMapper.setTestCaseFailedReason(result.getThrowable().getMessage());
            reportMapper.setTestCaseStatus("FAILED");
        } else
            reportMapper.setTestCaseStatus("SKIPPED");
        if (result.getMethod().getGroups().length != 0)
            reportMapper.setGroupName(Arrays.toString(result.getMethod().getGroups()));

        getTestCaseExecutionTimeValues(result);
        finalReportData.put(reportMapper.getTestCaseName(), reportMapper);
        ExtentReport.dumpDataForExtentReport(result, reportMapper);

    }

    public void getTestCaseExecutionTimeValues(ITestResult tr) {
        String pattern = "hh:mm:ss a";
        Date startTime = new Date(tr.getStartMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        reportMapper.setTestStartTime(simpleDateFormat.format(startTime));
        long time = tr.getEndMillis() - tr.getStartMillis();
        reportMapper.setTestCaseExecutionTime(String.valueOf(time));
        reportMapper.setTestCaseDescription(tr.getMethod().getDescription());
    }


    @AfterSuite(alwaysRun = true)
    public void generatingReportAndSendingEmail(ITestContext context)
            throws MessagingException, GeneralSecurityException, IOException {
        if (ExtentReport.extent != null) {
            ExtentReport.extent.flush();
        }
//        EmailUtility emailUtility = new EmailUtility();
//        if (ExtentListeners.failureCount > 0) {
//            emailUtility.sendEmailVersion2(context.getCurrentXmlTest().getSuite().getName() + "- Failed", "High",
//                    env, moduleName, teamName);
//        } else
//            emailUtility.sendEmailVersion2(context.getCurrentXmlTest().getSuite().getName() + "- Success", "",
//                    env, moduleName, teamName);
    }
}
