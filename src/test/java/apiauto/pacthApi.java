package apiauto;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class pacthApi {
    @Test
    public void putTestApi () {
        RestAssured.baseURI = "https://reqres.in";
        int userId= 3;
        String newName = "dea_sunara";

        String fname = given().when().get("api/users/" + userId)
                .getBody().jsonPath().get("first_name");
        System.out.println("new_before"+ fname);

        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("first_name", newName);
        JSONObject jsonObject = new JSONObject(bodyMap);

        given().log().all()
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .patch("api/users/" + userId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("first_name", Matchers.equalTo(newName));
    }
}
