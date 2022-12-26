import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class ApiTests {
//
    @Test
    public void getResponseStatus(){
        int statusCode=
                given()
                .queryParam("ISBN","9781449325862")
                .when()
                .get("https://bookstore.toolsqa.com/BookStore/v1")
                .getStatusCode();
        System.out.println("The response status is "+statusCode);
        Assert.assertEquals(statusCode,200);
    }
    @Test
    public void getPath(){
      String isbn= given()
              .queryParam("ISBN","9781449325862")
              .when()
              .get("https://bookstore.toolsqa.com/BookStore/v1/Book")
              .then().
              log().all()
              .extract()
              .jsonPath().getString("isbn");
        System.out.println(isbn);
    }

    @Test
    public void getBooks(){
      given()
                .when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Books")
                .then().log().all()
                 .assertThat()
                .body("books[2,5].isbn",hasItems("9781449337711","9781491950296"));

    }
    @Test
    public void validateResult(){
        given()
                .queryParam("ISBN","9781449325862")
                .when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Book")
                .then().log().all().assertThat()
                .body("isbn",equalTo("9781449325862"));
    }
}
