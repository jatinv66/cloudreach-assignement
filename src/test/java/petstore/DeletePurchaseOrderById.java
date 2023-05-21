package petstore;

import base.BaseTest;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class DeletePurchaseOrderById extends BaseTest {
    PetstoreHelper petstoreHelper = null;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void init() throws IOException {
        petstoreHelper = new PetstoreHelper();
    }

    @Test( description = "Delete Purchase Order By Id" ,groups = {"Petstore"})
    public void deletePurchaseOrderById() throws IOException, ParseException {
        System.out.println(Thread.currentThread().getId());
        Response response = petstoreHelper.deletePurchaseOrderById("1");
        softAssert = petstoreHelper.deletePurchaseOrderByIdAssertion(response,"200");
        softAssert.assertAll();
    }
}
