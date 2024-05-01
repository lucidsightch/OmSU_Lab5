import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.oneOf;

public class ChangePetTest {
    static RequestSpecification rs;

    @BeforeClass
    public static void setUp() {
        rs = given().
                baseUri("https://petstore.swagger.io/v2/pet").
                contentType(ContentType.JSON).
                body("""
                {
                  "id": 7,
                  "name": "kotenok-meow",
                  "photoUrls": [
                    "string",
                    "anotherString"
                  ]
                }""");
    }

    @Test
    public void checkResponseCode() {
        given(rs).
                put().
        then().
                statusCode(oneOf(200,201,202));
    }

    @Test
    public void notNullFields() {
        given(rs).
                put().
        then().
                body("name", equalTo("kotenok-meow")).
        and().
                body("photoUrls[0]", equalTo("string")).
        and().
                body("photoUrls[1]", equalTo("anotherString"));
    }
}
