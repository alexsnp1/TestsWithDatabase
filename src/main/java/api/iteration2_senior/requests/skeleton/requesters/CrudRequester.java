package api.iteration2_senior.requests.skeleton.requesters;

import api.iteration2_senior.models.BaseModel;
import api.iteration2_senior.requests.skeleton.HttpRequest;
import api.iteration2_senior.requests.skeleton.interfaces.CrudEndpointInterface;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class CrudRequester extends HttpRequest implements CrudEndpointInterface {

    public CrudRequester(RequestSpecification requestSpecification, Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        if (model == null) {
            throw new IllegalArgumentException("POST body cannot be null. Use post() instead");
        }
//        return given()
//                .spec(requestSpecification)
//                .body(model)
//                .post(endpoint.getUrl())
//                .then()
//                .assertThat()
//                .spec(responseSpecification);
//    }
        Response response = given()
                .spec(requestSpecification)
                .body(model)
                .post(endpoint.getUrl());

        if (response.statusCode() >= 500) {
            System.err.println("HTTP " + response.statusCode()
                    + " for POST " + endpoint.getUrl());
            System.err.println("Thread: " + Thread.currentThread().getName());
            System.err.println("Request body: " + model);
            System.err.println("Response body:\n" + response.asPrettyString());
        }
        if (endpoint == Endpoint.ADMIN_USERS && response.statusCode() != 201) {
            System.err.println("User creation failed: HTTP "
                    + response.statusCode());
            System.err.println("Thread: " + Thread.currentThread().getName());
            System.err.println("Response body:\n" + response.asPrettyString());
        }

        return response.then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
//    public ValidatableResponse post() {
//        return given()
//                .spec(requestSpecification)
//                .post(endpoint.getUrl())
//                .then()
//                .assertThat()
//                .spec(responseSpecification);
//    }
    public ValidatableResponse post() {
        Response response = given()
                .spec(requestSpecification)
                .post(endpoint.getUrl());

        if (response.statusCode() >= 500) {
            System.err.println("HTTP " + response.statusCode()
                    + " for POST " + endpoint.getUrl());
            System.err.println("Thread: " + Thread.currentThread().getName());
            System.err.println("Response body:\n" + response.asPrettyString());
        }
        if (endpoint == Endpoint.ACCOUNTS && response.statusCode() != 201) {
            System.err.println("Account creation failed: HTTP "
                    + response.statusCode());
            System.err.println("Thread: " + Thread.currentThread().getName());
            System.err.println("Response body:\n" + response.asPrettyString());
        }

        return response.then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
//    public ValidatableResponse get() {
//        return given()
//                .spec(requestSpecification)
//                .get(endpoint.getUrl())
//                .then()
//                .assertThat()
//                .spec(responseSpecification);
//    }
    public ValidatableResponse get() {
        Response response = given()
                .spec(requestSpecification)
                .get(endpoint.getUrl());

        if (response.statusCode() >= 500) {
            System.err.println("HTTP " + response.statusCode()
                    + " for GET " + endpoint.getUrl());
            System.err.println("Thread: " + Thread.currentThread().getName());
            System.err.println("Response body:\n" + response.asPrettyString());
        }

        return response.then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse put(BaseModel model) {
        var body = model == null ?
                "" : model;
        return given()
                .spec(requestSpecification)
                .body(body)
                .put(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public Object delete(BaseModel model) {
        return null;
    }
}
