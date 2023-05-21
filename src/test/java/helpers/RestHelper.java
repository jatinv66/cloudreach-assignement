package helpers;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT;

public class RestHelper {
    public Response doPostRequest(String baseURI, String endpoint, JSONObject header, JSONObject body) {
        RestAssured.baseURI = baseURI;
        RestAssured.defaultParser = Parser.JSON;
        RestAssuredConfig config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig());
        Response response = given().config(config).urlEncodingEnabled(false).log().uri().log().method().log().params()
                .log().body().contentType(ContentType.JSON).accept(ContentType.ANY).with().body(body).headers(header)
                .log().headers().relaxedHTTPSValidation().when().post(endpoint).then().log().all().extract().response();
        return response;
    }

    public Response doGetRequest(String baseURI, String endpoint, JSONObject header) {
        RestAssured.baseURI = baseURI;
        RestAssured.defaultParser = Parser.JSON;
        RestAssuredConfig config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig());
        Response response = given().config(config).urlEncodingEnabled(false).log().uri().log().method().log().params()
                .log().body().contentType(ContentType.JSON).accept(ContentType.ANY).with().headers(header)
                .relaxedHTTPSValidation().urlEncodingEnabled(false).when().get(endpoint).then().log().all().extract()
                .response();
        return response;
    }

    public Response doDeleteRequest(String baseURI,String endpoint, JSONObject header, JSONObject body) {
        RestAssured.baseURI = baseURI;
        RestAssured.defaultParser = Parser.JSON;
        RestAssuredConfig config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig());
        Response response = given().config(config).urlEncodingEnabled(false).log().uri().log().method().log().params()
                .log().body().contentType(ContentType.JSON).accept(ContentType.ANY).with().body(body).headers(header)
                .relaxedHTTPSValidation().urlEncodingEnabled(false).when().delete(endpoint).then().log().all().extract()
                .response();
        return response;
    }
}
