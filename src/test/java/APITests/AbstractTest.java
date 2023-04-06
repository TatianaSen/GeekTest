package APITests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String baseUrl;
    private static String token;
    private static String header;
    private static String validUsername;
    private static String validPassword;
    private static String invalidUsername;
    private static String invalidPassword;

    protected static RequestSpecification requestSpecificationValidDataForLogin;
    protected static RequestSpecification requestSpecificationInvalidUsername;
    protected static RequestSpecification requestSpecificationInvalidPassword;
    protected static ResponseSpecification responseSpecificationValidDataForLogin;
    protected static ResponseSpecification responseSpecificationInvalidDataForLogin;
    protected static RequestSpecification requestSpecificationMyPosts;
    protected static RequestSpecification requestSpecificationNotMyPosts;
    protected static ResponseSpecification responseSpecificationPostsValidData;
    protected static ResponseSpecification responseSpecificationPostsInvalidData;
    protected static RequestSpecification requestSpecificationMyPostsNoAuth;
    protected static RequestSpecification requestSpecificationNotMyPostsNoAuth;



    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);
        baseUrl = prop.getProperty("base.url");
        token = prop.getProperty("token");
        header = prop.getProperty("header");
        validUsername = prop.getProperty("validUsername");
        validPassword = prop.getProperty("validPassword");
        invalidUsername = prop.getProperty("invalidUsername");
        invalidPassword = prop.getProperty("invalidPassword");

        requestSpecificationValidDataForLogin = new RequestSpecBuilder()
                .addFormParam("username", validUsername)
                .addFormParam("password",validPassword)
                .log(LogDetail.URI)
                .build();
        requestSpecificationInvalidUsername = new RequestSpecBuilder()
                .addFormParam("username", invalidUsername)
                .addFormParam("password", validPassword)
                .log(LogDetail.URI)
                .build();
        requestSpecificationInvalidPassword = new RequestSpecBuilder()
                .addFormParam("username", invalidPassword)
                .addFormParam("password", invalidPassword)
                .log(LogDetail.URI)
                .build();
        responseSpecificationValidDataForLogin = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.URI)
                .log(LogDetail.STATUS)
                .build();

        responseSpecificationInvalidDataForLogin = new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectStatusLine("HTTP/1.1 401 Unauthorized")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.URI)
                .log(LogDetail.STATUS)
                .build();
        requestSpecificationMyPosts = new RequestSpecBuilder()
                .addHeader(header,token)
                .addQueryParam("sort", "createdAt")
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();
        requestSpecificationMyPostsNoAuth = new RequestSpecBuilder()
                .addQueryParam("sort", "createdAt")
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();
        requestSpecificationNotMyPosts = new RequestSpecBuilder()
                .addHeader(header,token)
                .addQueryParam("owner","notMe")
                .addQueryParam("sort", "createdAt")
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();
        requestSpecificationNotMyPostsNoAuth = new RequestSpecBuilder()
                .addQueryParam("owner","notMe")
                .addQueryParam("sort", "createdAt")
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();
        responseSpecificationPostsValidData = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.URI)
                .log(LogDetail.STATUS)
                .build();
        responseSpecificationPostsInvalidData = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.URI)
                .log(LogDetail.STATUS)
                .build();



    }

    public static RequestSpecification getRequestSpecificationValidDataForLogin() {
        return requestSpecificationValidDataForLogin;
    }

    public static RequestSpecification getRequestSpecificationInvalidUsername() {
        return requestSpecificationInvalidUsername;
    }

    public static RequestSpecification getRequestSpecificationInvalidPassword() {
        return requestSpecificationInvalidPassword;
    }

    public static ResponseSpecification getResponseSpecificationInvalidDataForLogin() {
        return responseSpecificationInvalidDataForLogin;
    }

    public static RequestSpecification getRequestSpecificationMyPosts() {
        return requestSpecificationMyPosts;
    }

    public static RequestSpecification getRequestSpecificationNotMyPosts() {
        return requestSpecificationNotMyPosts;
    }

    public static ResponseSpecification getResponseSpecificationPostsValidData() {
        return responseSpecificationPostsValidData;
    }

    public static ResponseSpecification getResponseSpecificationPostsInvalidData() {
        return responseSpecificationPostsInvalidData;
    }

    public static ResponseSpecification getResponseSpecificationValidDataForLogin() {
        return responseSpecificationValidDataForLogin;
    }

    public static RequestSpecification getRequestSpecificationMyPostsNoAuth() {
        return requestSpecificationMyPostsNoAuth;
    }

    public static RequestSpecification getRequestSpecificationNotMyPostsNoAuth() {
        return requestSpecificationNotMyPostsNoAuth;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
