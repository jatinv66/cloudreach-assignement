package helpers;

import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

public class HelperMethods {

    public boolean checkKeyExist(String key,Response res){
        try {
          if(res.path(key).toString().equals("[]"))
                throw new NullPointerException();
        }
        catch (NullPointerException e){
            return false;
        }
        return true;
    }
    public void verifyKeysIsPresent(ArrayList<String> keysArr, Response response,SoftAssert sa){
        try {
            ArrayList<String> keyNotPresent = new ArrayList<>();
            boolean flag = true;
            for (int i = 0; i < keysArr.size(); i++) {
                if (!checkKeyExist(keysArr.get(i), response)) {
                    flag = false;
                    keyNotPresent.add(keysArr.get(i));
                }
            }
            if (!flag) {
                sa.fail("your passes array these keys is not present in response - : " + keyNotPresent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void verifyResponseCode(String statusCode, String listOfStatusCode,SoftAssert sa) {
        try {
            boolean flag = false;
            String[] statusCodeToVerify = listOfStatusCode.split(":");
            for (String value : statusCodeToVerify) {
                if (statusCode.equals(value)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                sa.fail("Response Do not match!! Expected Value of Response Code " + listOfStatusCode
                        + " But found "
                        + statusCode);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void matchKeysValueFromResponse(ArrayList<String>keysValueArr,Response response,SoftAssert sa){
        ArrayList<String> notMatchedKeyValue = new ArrayList<>();
        boolean flag = true;
        for(int i = 0 ;i < keysValueArr.size(); i++){
            String[] splitStrings = keysValueArr.get(i).split("=");
            if( !checkKeyExist(splitStrings[0],response) || !response.path(splitStrings[0]).toString().contentEquals(splitStrings[1])){
                flag = false;
                notMatchedKeyValue.add(keysValueArr.get(i));
            }
        }
        if(!flag){
            sa.fail("Assertion failed due key is not found or value is not match with response - : "+notMatchedKeyValue);
        }
    }

    public Object readDataFromJSONSimpleFile(String path) throws IOException, ParseException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        JSONParser parser = new JSONParser();
        return parser.parse(IOUtils.toString(fileInputStream));
    }
}
