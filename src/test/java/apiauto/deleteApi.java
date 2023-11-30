package apiauto;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class deleteApi {
    @Test
    public void deleteTestApi () {
        RestAssured.baseURI = "https://reqres.in";
        int deletUser = 4;

        given().log().all()
                .when().delete("api/users/" + deletUser)
                .then().log().all()
                .assertThat().statusCode(204);
    }
}
