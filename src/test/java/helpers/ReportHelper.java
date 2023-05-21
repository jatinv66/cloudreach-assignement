package helpers;

import base.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class ReportHelper extends BaseTest {
    public void getAllTestDetails(String requestType, Object reqBody, JSONObject reqHeader, String url,
                                  String baseURI,int testcaseType) {
        reportMapper.setRequestBody(reqBody.toString());
        reportMapper.setRequestType(requestType);
        if (url.contains("https"))
	    reportMapper.setRequestURL(url);
        else
            reportMapper.setRequestURL(baseURI + "/" + url);

	    reportMapper.setRequestHeader(reqHeader.toString());
        if (testcaseType == 0)
            reportMapper.setTestCaseType("POSITIVE");
        else
            reportMapper.setTestCaseType("NEGATIVE");
           reportMapper.setCurlForAPI(curlGenerator(requestType, reportMapper.getRequestURL(), reqHeader, reportMapper.getRequestBody()));
    }

    public String curlGenerator(String requestTypeForCurl, String urlForCurl, JSONObject headerForCurl,
                                     String reqBody) {
        HashMap<String, Object> yourHashMap = new Gson().fromJson(headerForCurl.toString(), HashMap.class);
        String prettyJson = "";
        String allHeadKeyAndValue = "";
        for (String key : yourHashMap.keySet()) {
            allHeadKeyAndValue += " -H '" + key + ": " + yourHashMap.get(key) + "' ";
        }
        if (requestTypeForCurl.equals("POST") || requestTypeForCurl.equals("DELETE")
                || requestTypeForCurl.equals("PATCH") || requestTypeForCurl.equals("PUT"))
            if (!allHeadKeyAndValue.contains("Content-Type"))
                allHeadKeyAndValue += " -H 'Content-Type: application/json; charset=UTF-8' ";
        if (reqBody.equals("NA") || reqBody.equals("{}")) {
                System.out.println("Body is Empty!");
        } else
            prettyJson = formatJsonString(reqBody);
        String curl = "";
        curl += "curl -X" + requestTypeForCurl + " " + allHeadKeyAndValue;
        if (!prettyJson.equals(""))
            curl += " -d '" + prettyJson + "'";
        curl += "\n'" + urlForCurl + "'";
        System.out.println(curl);
        return curl;
    }

    public String formatJsonString(String json) {
        ObjectMapper mapper = new ObjectMapper();
        String prettyJson = "";
        try {
            Object jsonObject = mapper.readValue(json, Object.class);
            prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prettyJson;
    }

    public void getAPIResponse(String response, String statusCode) {
            reportMapper.setApiResponse(response);
        reportMapper.setGetAPIStatusCode(statusCode);
        reportMapper.setConsoleMessage(response);
    }
}
