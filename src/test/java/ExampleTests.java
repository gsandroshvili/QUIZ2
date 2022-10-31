import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ExampleTests {

    @Test
    public void wrongFormatUser(){
        given()
                .body("""
                        {
                          "userName": 1,
                          "password": "string"
                        }"""
                )
                .contentType("application/json")
                .post("https://bookstore.toolsqa.com/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(equalTo(400))
                .body("code", equalTo("1300"))
                .body("username", equalTo(null));
    }

    @Test
    public void correctFormatUser(){
        given()
                .body("""
                        {
                          "userName": "randomGeorgianUser",
                          "password": "@!#randomGeorgianPassword123."
                        }"""
                )
                .contentType("application/json")
                .post("https://bookstore.toolsqa.com/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(equalTo(201))
                .body("username", equalTo("randomGeorgianUser"));
    }

    @Test
    public void emptyUser(){
        given()
                .body("""
                        {
                        }"""
                )
                .contentType("application/json")
                .post("https://bookstore.toolsqa.com/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(equalTo(400))
                .body("code", equalTo("1200"))
                .body("username", equalTo(null));
    }
}
