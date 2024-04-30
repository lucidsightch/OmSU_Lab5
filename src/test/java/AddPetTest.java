import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddPetTest {
    static RequestSpecification rs;

    @BeforeClass
     public static void setUp() {
        rs = given().
                baseUri("https://petstore.swagger.io/v2/pet").
                contentType(ContentType.JSON).
                body("""
                {
                  "id": 11,
                  "name": "kotenok-gav",
                  "photoUrls": [
                    "string"
                  ]
                }""");
    }

    @Test
    public void checkResponseCode() {
        given(rs).
                post().
        then().
                statusCode(oneOf(200,201,202));
    }

    @Test
    public void notIncludeError() {
        given(rs).
                post().
        then().
                body("$", not(containsString("error")));
    }

    @Test
    public void containJsonBody() {
        given(rs).
                post().
        then().
                body("$", notNullValue() );
    }
}
