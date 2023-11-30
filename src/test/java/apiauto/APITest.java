package apiauto;


import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

public class APITest {
    @Test
    public void getuseTest (){

        File jsonSchema = new File("src/test/resources/newjsonschema/getListUseSchema.json");

        RestAssured
                .given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200)
                .assertThat().body("per_page", Matchers.equalTo(6))
                .assertThat().body("page", Matchers.equalTo(2))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));

    }
    @Test
    public void postUseTest (){
        String valuename ="dea";
        String valuejob ="Qa";

        JSONObject bodyobj = new JSONObject();
        bodyobj.put("name", valuename);
        bodyobj.put("job", valuejob);

        RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bodyobj.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("name", Matchers.equalTo(valuename));
    }
}
