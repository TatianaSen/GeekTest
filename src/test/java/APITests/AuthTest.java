package APITests;

import API.service.Endpoints;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class  AuthTest extends AbstractTest{
    @Test
    @Tag("Positive")
    void postAuthValidDataTest(){
        given()
                .spec(getRequestSpecificationValidDataForLogin())
                .when()
                .post(getBaseUrl()+ Endpoints.postAuth)
                .then()
                .spec(getResponseSpecificationValidDataForLogin());
    }

    @Test
    @Tag("Negative")
    void postAuthInvalidUsernameTest(){
        given()
                .spec(getRequestSpecificationInvalidUsername())
                .when()
                .post(getBaseUrl()+Endpoints.postAuth)
                .then()
                .spec(getResponseSpecificationInvalidDataForLogin());
    }

    @Test
    @Tag("Negative")
    void postAuthInvalidPasswordTest(){
        given()
                .spec(getRequestSpecificationInvalidPassword())
                .when()
                .post(getBaseUrl()+Endpoints.postAuth)
                .then()
                .spec(getResponseSpecificationInvalidDataForLogin());
    }

}
