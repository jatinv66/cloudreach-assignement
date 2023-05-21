package petstore;

import base.BaseTest;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FindPetByStatus extends BaseTest {
    PetstoreHelper petstoreHelper = null;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void init() throws IOException {
        petstoreHelper = new PetstoreHelper();
    }

    @Test( description = "Find Pet By Status - {sold}" ,groups = {"Petstore"})
    public void findPetBySoldStatus() throws IOException, ParseException {
        Response response = petstoreHelper.findPetByStatus("sold");
        softAssert = petstoreHelper.findPetBySoldStatusAssertion(response,"200");
        softAssert.assertAll();
    }

    @Test( description = "Find Pet By Status - {available}" ,groups = {"Petstore"})
    public void findPetByAvailableStatus() throws IOException, ParseException {
        Response response = petstoreHelper.findPetByStatus("available");
        softAssert = petstoreHelper.findPetByAvailableStatusAssertion(response,"200");
        softAssert.assertAll();
    }

    @Test( description = "Find Pet By Status - {pending}" ,groups = {"Petstore"})
    public void findPetByPendingStatus() throws IOException, ParseException {
        Response response = petstoreHelper.findPetByStatus("pending");
        softAssert = petstoreHelper.findPetByPendingStatusAssertion(response,"200");
        softAssert.assertAll();
    }
}
