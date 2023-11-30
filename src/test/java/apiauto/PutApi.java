package apiauto;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class PutApi {

    @Test
    public void testputuser() {
        RestAssured.baseURI = "https://reqres.in";
        int userId = 2;
        String newName = "updateUser";

        String fname = given().when().get("api/users/" + userId).getBody().jsonPath().get("data.first_name");
        String lname = given().when().get("api/users/" + userId).getBody().jsonPath().get("data.last_name");
        String avatar = given().when().get("api/users/" + userId).getBody().jsonPath().get("data.avatar");
        String email = given().when().get("api/users/" + userId).getBody().jsonPath().get("data.email");
        System.out.println("name before" + fname);

        HashMap<String, Object> bodyMaps = new HashMap<>();
        bodyMaps.put("Id", userId);
        bodyMaps.put("email", email);
        bodyMaps.put("first_name", newName);
        bodyMaps.put("last_name", lname);
        bodyMaps.put("avatar", avatar);
        JSONObject jsonObject = new JSONObject(bodyMaps);

        given().log().all()
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .put("api/user/" + userId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("first_name", Matchers.equalTo(newName));
    }
}
