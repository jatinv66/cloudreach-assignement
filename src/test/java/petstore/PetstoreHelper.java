package petstore;

import base.BaseTest;
import helpers.HelperMethods;
import helpers.ReportHelper;
import helpers.RestHelper;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class PetstoreHelper extends BaseTest {
    RestHelper restHelper= new RestHelper();
    ReportHelper reportHelper = new ReportHelper();
    JSONObject reqHeaders= new JSONObject();
    JSONObject reqBody= new JSONObject();
    HelperMethods helperMethods = new HelperMethods();
    String baseURI = "https://petstore.swagger.io/v2/";

    private Path placeOrderForPetPayload() {
        return Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "testData", "PlaceOrderForPet.json");
    }
    private Path addPetToStorePayload() {
        return Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "testData", "AddPetToStore.json");
    }
    public Response addPetToStore() throws IOException, ParseException {
        reportMapper.setTestCaseName("addPetToStore");
        JSONObject reqBody = (JSONObject) helperMethods.readDataFromJSONSimpleFile(addPetToStorePayload().toString());
        reportHelper.getAllTestDetails("POST", reqBody, reqHeaders, baseURI +"pet" , baseURI,0);
        Response response = restHelper.doPostRequest(baseURI, "pet", reqHeaders,reqBody);
        reportHelper.getAPIResponse(response.asString(), String.valueOf(response.getStatusCode()));
        return response;
    }
    public Response findPetByStatus(String status) throws IOException, ParseException {
        reportMapper.setTestCaseName("findPetByStatus");
        addPetToStore();
        reportHelper.getAllTestDetails("GET", reqBody, reqHeaders, baseURI +"pet/findByStatus?status="+status , baseURI,0);
        Response response = restHelper.doGetRequest(baseURI, "pet/findByStatus?status="+status, reqHeaders);
        reportHelper.getAPIResponse(response.asString(), String.valueOf(response.getStatusCode()));
        return response;
    }

    public Response findPetById(String id) throws IOException, ParseException {
        reportMapper.setTestCaseName("findPetById");
        reportHelper.getAllTestDetails("GET", reqBody, reqHeaders, baseURI +"pet/"+id , baseURI,0);
        Response response = restHelper.doGetRequest(baseURI, "pet/"+id, reqHeaders);
        reportHelper.getAPIResponse(response.asString(), String.valueOf(response.getStatusCode()));
        return response;
    }

    public Response placeOrderForPet() throws IOException, ParseException {
        reportMapper.setTestCaseName("placeOrderForPet");
        reportHelper.getAllTestDetails("POST", reqBody, reqHeaders, baseURI +"store/order" , baseURI,0);
        JSONObject reqBody = (JSONObject) helperMethods.readDataFromJSONSimpleFile(placeOrderForPetPayload().toString());
        Response response = restHelper.doPostRequest(baseURI, "store/order",reqHeaders, reqBody);
        reportHelper.getAPIResponse(response.asString(), String.valueOf(response.getStatusCode()));
        return response;
    }

    public Response findPurchaseOrderById(String id) throws IOException, ParseException {
        reportMapper.setTestCaseName("findPurchaseOrderById");
        reportHelper.getAllTestDetails("GET", reqBody, reqHeaders, baseURI +"store/order/"+id , baseURI,0);
        Response response = restHelper.doGetRequest(baseURI, "store/order/"+id,reqHeaders);
        reportHelper.getAPIResponse(response.asString(), String.valueOf(response.getStatusCode()));
        return response;
    }

    public Response deletePurchaseOrderById(String id) throws IOException, ParseException {
        reportMapper.setTestCaseName("deletePurchaseOrderById");
        reportHelper.getAllTestDetails("DELETE", reqBody, reqHeaders, baseURI +"store/order/"+id , baseURI,0);
        Response response = restHelper.doDeleteRequest(baseURI, "store/order/"+id,reqHeaders, reqBody);
        reportHelper.getAPIResponse(response.asString(), String.valueOf(response.getStatusCode()));
        return response;
    }

    public SoftAssert findPetBySoldStatusAssertion(Response response,
                                               String statusCode) {
        SoftAssert softAssert = new SoftAssert();
        helperMethods.verifyResponseCode(String.valueOf(response.getStatusCode()), statusCode,softAssert);
        ArrayList responseJsonArray =  response.path("");
        ArrayList<String> presentKeyCheckArr = new ArrayList<>();
        for(int i =0 ;i<responseJsonArray.size();i++){
            presentKeyCheckArr.add("["+i+"]."+"status");
        }
        helperMethods.verifyKeysIsPresent(presentKeyCheckArr,response,softAssert);
        ArrayList<String> matchValueArr = new ArrayList<>();
        for(int i =0 ;i<responseJsonArray.size();i++){
            matchValueArr.add("["+i+"]."+"status=pending");
        }
        return softAssert;
    }

    public SoftAssert findPetByAvailableStatusAssertion(Response response,
                                               String statusCode) {
        SoftAssert softAssert = new SoftAssert();
        helperMethods.verifyResponseCode(String.valueOf(response.getStatusCode()), statusCode,softAssert);
        ArrayList responseJsonArray =  response.path("");
        ArrayList<String> presentKeyCheckArr = new ArrayList<>();
        for(int i =0 ;i<responseJsonArray.size();i++){
            presentKeyCheckArr.add("["+i+"]."+"status");
        }
        helperMethods.verifyKeysIsPresent(presentKeyCheckArr,response,softAssert);
        ArrayList<String> matchValueArr = new ArrayList<>();
        for(int i =0 ;i<responseJsonArray.size();i++){
            matchValueArr.add("["+i+"]."+"status=pending");
        }
        return softAssert;
    }

    public SoftAssert findPetByPendingStatusAssertion(Response response,
                                               String statusCode) {
        SoftAssert softAssert = new SoftAssert();
        helperMethods.verifyResponseCode(String.valueOf(response.getStatusCode()), statusCode,softAssert);
        ArrayList responseJsonArray =  response.path("");
        ArrayList<String> presentKeyCheckArr = new ArrayList<>();
        for(int i =0 ;i<responseJsonArray.size();i++){
            presentKeyCheckArr.add("["+i+"]."+"status");
        }
        helperMethods.verifyKeysIsPresent(presentKeyCheckArr,response,softAssert);
        ArrayList<String> matchValueArr = new ArrayList<>();
        for(int i =0 ;i<responseJsonArray.size();i++){
            matchValueArr.add("["+i+"]."+"status=pending");
        }
        helperMethods.matchKeysValueFromResponse(matchValueArr,response,softAssert);
        return softAssert;
    }

    public SoftAssert findPetByIdAssertion(Response response,
                                               String statusCode) {
        SoftAssert softAssert = new SoftAssert();
        helperMethods.verifyResponseCode(String.valueOf(response.getStatusCode()), statusCode,softAssert);
        ArrayList<String> arr = new ArrayList<>(
                Arrays.asList(
                        "id"
                ));
        helperMethods.verifyKeysIsPresent(arr,response,softAssert);
        return softAssert;
    }

    public SoftAssert placeOrderForPetAssertion(Response response,
                                               String statusCode) {
        SoftAssert softAssert = new SoftAssert();
        helperMethods.verifyResponseCode(String.valueOf(response.getStatusCode()), statusCode,softAssert);
        ArrayList<String> arr = new ArrayList<>(
                Arrays.asList(
                        "id",
                        "status"
                ));
        helperMethods.verifyKeysIsPresent(arr,response,softAssert);
        ArrayList<String> matchValueArr = new ArrayList<>(
                Arrays.asList(
                    "status=placed"
                )
        );
        helperMethods.matchKeysValueFromResponse(matchValueArr,response,softAssert);
        return softAssert;
    }

    public SoftAssert findPurchaseOrderByIdAssertion(Response response,
                                               String statusCode) {
        SoftAssert softAssert = new SoftAssert();
        helperMethods.verifyResponseCode(String.valueOf(response.getStatusCode()), statusCode,softAssert);
        ArrayList<String> arr = new ArrayList<>(
                Arrays.asList(
                        "id"
                ));
        helperMethods.verifyKeysIsPresent(arr,response,softAssert);
        ArrayList<String> matchValueArr = new ArrayList<>(
                Arrays.asList(
                        "status=placed",
                        "complete=true"
                )
        );
        helperMethods.matchKeysValueFromResponse(matchValueArr,response,softAssert);
        return softAssert;
    }

    public SoftAssert deletePurchaseOrderByIdAssertion(Response response,
                                               String statusCode) {
        SoftAssert softAssert = new SoftAssert();
        helperMethods.verifyResponseCode(String.valueOf(response.getStatusCode()), statusCode,softAssert);
        ArrayList<String> arr = new ArrayList<>(
                Arrays.asList(
                        "code"
                ));
        helperMethods.verifyKeysIsPresent(arr,response,softAssert);
        ArrayList<String> matchValueArr = new ArrayList<>(
                Arrays.asList(
                        "code=200"
                )
        );
        helperMethods.matchKeysValueFromResponse(matchValueArr,response,softAssert);
        return softAssert;
    }
}
