package petstore;

import base.BaseTest;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FindPurchaseOrderById extends BaseTest {
    PetstoreHelper petstoreHelper = null;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void init() throws IOException {
        petstoreHelper = new PetstoreHelper();
    }

    @Test( description = "Find Purchase Order By Id" ,groups = {"Petstore"})
    public void findPurchaseOrderById() throws IOException, ParseException {
        Response response = petstoreHelper.findPurchaseOrderById("1");
        softAssert = petstoreHelper.findPurchaseOrderByIdAssertion(response,"200");
        softAssert.assertAll();
    }
}
