package petstore;

import base.BaseTest;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FindPetById extends BaseTest {
    PetstoreHelper petstoreHelper = null;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void init() throws IOException {
        petstoreHelper = new PetstoreHelper();
    }

    @Test( description = "Find Pet By Id" ,groups = {"Petstore"})
    public void findPetById() throws IOException, ParseException {
        Response response = petstoreHelper.findPetById("1");
        softAssert = petstoreHelper.findPetByIdAssertion(response,"200");
        softAssert.assertAll();
    }
}
