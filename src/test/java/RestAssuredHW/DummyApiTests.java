package RestAssuredHW;


import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class DummyApiTests {
    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
    }

    @Test
    public void positiveTest() {
        get("/api/v1/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id[2]", Matchers.is(3), "data.employee_name[2]", Matchers.equalTo("Garrett Winters"));
    }

    @Test
    public void negativeTest() {
        get("/api/v1/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("ololo"))
                .body("data.id[2]", Matchers.is(2), "data.employee_name[2]", Matchers.equalTo("Garrett Winters"));
    }

    @Test
    public void positiveTestEmployee1() {
        get("/api/v1/employee/1")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("message", equalTo("Successfully! Record has been fetched."))
                .body("data.id", Matchers.is(1), "data.employee_name", Matchers.equalTo("Tiger Nixon"));
    }

    @Test
    public void negativeTestEmployee1() {
        get("/api/v1/employee/1")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("ololo"))
                .body("message", equalTo("Hello world!"))
                .body("data.id[2]", Matchers.is(22), "data.employee_name", Matchers.equalTo("Tiger Nixon"));
    }

    @Test
    public void deleteTestEmployeePositive() {
        when()
                .request("DELETE", "/api/v1/delete/2")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));


    }
    @Test
    public void testPostPositive(){
        PostResponse postResponseObject = new PostResponse("test","123","23");
        PostResponseModel expectedResponseModelObject = new PostResponseModel("success", new Data("test", "123","23"), "Successfully! Record has been added.");

        PostResponseModel responseModelObject = given()
                .with()
                .contentType("application/json")
                .body(postResponseObject)
                .log().all()
                .when()
                .request("POST","/api/v1/create")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .as(PostResponseModel.class);
        assertEquals(responseModelObject, expectedResponseModelObject);
    }

    @Test
    public void testPostNegative(){
        PostResponse postResponseObject = new PostResponse("test","123","23");
        PostResponseModel expectedResponseModelObject = new PostResponseModel("success", new Data("test", "124","23"), "Successfully! Record has been added.");

        PostResponseModel responseModelObject = given()
                .with()
                .contentType("application/json")
                .body(postResponseObject)
                .log().all()
                .when()
                .request("POST","/api/v1/create")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .as(PostResponseModel.class);
        assertEquals(responseModelObject, expectedResponseModelObject);
    }
    @Test
    public void deletePositive() {
        when()
                .request("DELETE", "/api/v1/delete/2")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data", equalTo("2"))
                .body("message", equalTo("Successfully! Record has been deleted"));
}

    @Test
    public void deleteNegative() {
        when()
                .request("DELETE", "/api/v1/delete/2")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data", equalTo("2"))
                .body("message", equalTo("Successfully! Record has been del"));
    }

    @Test
    public void putPositive() {
        PostResponse postResponseObject = new PostResponse("test","123","23");
        PostResponseModel expectedResponseModelObject = new PostResponseModel("success", new Data("test", "123","23"), "Successfully! Record has been updated.");

        PostResponseModel responseModelObject = given()
        .with()
                .contentType("application/json")
                .body(postResponseObject)
                .log().all()
        .when()
                .request("PUT", "/api/v1/update/21")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(PostResponseModel.class);
        assertEquals(responseModelObject, expectedResponseModelObject);
    }
    @Test
    public void putNegative() {
        PostResponse postResponseObject = new PostResponse("test","123","23");
        PostResponseModel expectedResponseModelObject = new PostResponseModel("success", new Data("test", "124","23"), "Successfully! Record has been updated.");

        PostResponseModel responseModelObject = given()
                .with()
                .contentType("application/json")
                .body(postResponseObject)
                .log().all()
                .when()
                .request("PUT", "/api/v1/update/21")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(PostResponseModel.class);
        assertEquals(responseModelObject, expectedResponseModelObject);
    }
    }
