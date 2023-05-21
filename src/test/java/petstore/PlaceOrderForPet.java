package petstore;

import base.BaseTest;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class PlaceOrderForPet extends BaseTest {
    PetstoreHelper petstoreHelper = null;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void init() throws IOException {
        petstoreHelper = new PetstoreHelper();
    }

    @Test( description = "Place Order for pet" ,groups = {"Petstore"})
    public void placeOrderForPet() throws IOException, ParseException {
        Response response = petstoreHelper.placeOrderForPet();
        softAssert = petstoreHelper.placeOrderForPetAssertion(response,"200");
        softAssert.assertAll();
    }
}
