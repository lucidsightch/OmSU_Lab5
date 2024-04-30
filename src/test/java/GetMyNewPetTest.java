import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetMyNewPetTest {
    static RequestSpecification rs;

    @BeforeClass
    public static void setUp() {
        rs = given().
                baseUri("https://petstore.swagger.io/v2/pet/11");
    }

    @Test
    public void checkResponseCode() {
        given(rs).
                get().
        then().
                statusCode(oneOf(200,201,202));
    }

    @Test
    public void notIncludeError() {
        given(rs).
                get().
        then().
                body("$", not(containsString("error")));
    }

    @Test
    public void containJsonBody() {
        given(rs).
                get().
        then().
                body("$", notNullValue() );
    }

    @Test
    public void notNullFields() {
        given(rs).
                get().
        then().
                body("name", notNullValue()).
        and().
                body("photoUrls", notNullValue());
    }
}
