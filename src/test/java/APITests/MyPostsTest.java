package APITests;

import API.service.Endpoints;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class MyPostsTest extends AbstractTest{

    @Test
    @Tag("Positive")
    void getMyPostsValidQueries(){
        given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam("order","ASC")
                .queryParam("page","1")
                .when()
                .get(getBaseUrl()+ Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValidData());
    }
    @Test
    @Tag("Negative")
    void  getMyPostsNegativePage(){
        given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam("order","DESC")
                .queryParam("page","-1")
                .when()
                .get(getBaseUrl()+Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsInvalidData());
    }

    @Test
    @Tag("Negative")
    void getMyPostsEmptyPage(){
        JsonPath response = (JsonPath) given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam("order","DESC")
                .queryParam("page","")
                .when()
                .get(getBaseUrl()+Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsInvalidData());
    }
    @Test
    @Tag("Negative")
    void getMyPostsSpecSymbolsPage(){
        given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam("order","ASC")
                .queryParam("page","@#$%")
                .when()
                .get(getBaseUrl()+Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsInvalidData());
    }
    @Test
    @Tag("Negative")
    void getMyPostsWithoutAuth(){
        JsonPath response = (JsonPath) given()
                .spec(getRequestSpecificationMyPostsNoAuth())
                .queryParam("order","ASC")
                .queryParam("page","1")
                .when()
                .get(getBaseUrl()+ Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationInvalidDataForLogin());
        assertThat(response.getString("message"), equalTo("Auth header required X-Auth-Token"));
    }
}
