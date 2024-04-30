import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.oneOf;

public class DeletePetTest {
    static RequestSpecification rs;

    @BeforeClass
    public static void setUp() {
        given().
                contentType(ContentType.JSON).
                body("""
                {
                  "id": 999,
                  "name": "kotenok-gav",
                  "photoUrls": [
                    "string"
                  ]
                }""").
        post("https://petstore.swagger.io/v2/pet");
        rs = given().
                baseUri("https://petstore.swagger.io/v2/pet/999");
    }

    @Test
    public void checkResponseCode() {
        given(rs).
                delete().
        then().
                statusCode(oneOf(200,201,202));
    }
}
