package helpers;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ReportMapper {

    //Default values for all Custom Parameters
    public String testCaseName="NA";
    public String testCaseStatus="NA";
    public String testCaseFailedReason="NA";
    public String testCaseExecutionTime="NA";
    public String testStartTime="NA";
    public String testCaseDescription="NA";
    public String requestBody="NA";
    public String requestHeader="NA";
    public String requestType="NA";
    public String requestURL="NA";
    public String apiResponse="NA";
    public String testCaseType="NA";
    public String getAPIStatusCode="NA or Status Code note Received";
    public String curlForAPI="NA";
    public String groupName="NA";
    public String consoleMessage = "";
    public ArrayList<String> apiCallsName = new ArrayList<>();
    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
	if (this.testCaseName.contains("NA"))
	    this.testCaseName = this.testCaseName.replace("NA", "");

	this.testCaseName = testCaseName;
	apiCallsName.add(testCaseName + " ");
    }

    public void setTestCaseStatus(String testCaseStatus) {
        this.testCaseStatus = testCaseStatus;
    }

    public String getTestCaseFailedReason() {
        return testCaseFailedReason;
    }

    public void setTestCaseFailedReason(String testCaseFailedReason) {
	this.testCaseFailedReason = testCaseFailedReason;
    }

    public String getTestCaseExecutionTime() {
        return testCaseExecutionTime;
    }

    public void setTestCaseExecutionTime(String testCaseExecutionTime) {
        this.testCaseExecutionTime = testCaseExecutionTime;
    }

    public void setTestStartTime(String testStartTime) {
        this.testStartTime = testStartTime;
    }

    public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void setRequestHeader(String requestHeader) {
	this.requestHeader = requestHeader;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
	this.requestURL = requestURL;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(String apiResponse) {
	this.apiResponse += ":::" + apiResponse;
    }

    public String getTestCaseType() {
        return testCaseType;
    }

    public void setTestCaseType(String testCaseType) {
        this.testCaseType = testCaseType;
    }

    public String getGetAPIStatusCode() {
        return getAPIStatusCode;
    }

    public void setGetAPIStatusCode(String getAPIStatusCode) {
        this.getAPIStatusCode += ":::" + getAPIStatusCode;
    }

    public String getCurlForAPI() {
        return curlForAPI;
    }
    public String getRequestBody() {
        return requestBody;
    }

    public void setCurlForAPI(String curlForAPI) {
	this.curlForAPI += ":::" + curlForAPI;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setConsoleMessage(String consoleMessage) {
	this.consoleMessage += consoleMessage + "________";
    }
}
