package APITests;

import API.service.Endpoints;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NotMyPostsTest extends AbstractTest{
    @Test
    @Tag("Positive")
    void getNotMyPostsValidQueries(){
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam("order","ASC")
                .queryParam("page","1")
                .when()
                .get(getBaseUrl()+ Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValidData());
    }
    @Test
    @Tag("Negative")
    void  getNotMyPostsNegativePage(){
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam("order","DESC")
                .queryParam("page","-1")
                .when()
                .get(getBaseUrl()+Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsInvalidData());
    }

    @Test
    @Tag("Negative")
    void getNotMyPostsEmptyPage(){
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam("order","DESC")
                .queryParam("page","")
                .when()
                .get(getBaseUrl()+Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsInvalidData());
    }
    @Test
    @Tag("Positive")
    void getMyPostsOrderALL(){
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam("order","ALL")
                .queryParam("page","2")
                .when()
                .get(getBaseUrl()+Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValidData());
    }
    @Test
    @Tag("Negative")
    void getNotMyPostsWithoutAuth(){
        JsonPath response = (JsonPath)given()
                .spec(getRequestSpecificationNotMyPostsNoAuth())
                .queryParam("order","ASC")
                .queryParam("page","1")
                .when()
                .get(getBaseUrl()+ Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationInvalidDataForLogin());
        assertThat(response.getString("message"), equalTo("Auth header required X-Auth-Token"));
    }
}
