package com.OAuth.playlIst.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.OAuth.playlIst.api.ApplicationAPI.TokenManager.get_token;

public class SpecBuilder {


    public static RequestSpecification get_req_specification() {

        String access_token = get_token();

        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_URI"))
//                .setBaseUri("https://api.spotify.com")
                .setBasePath("v1")
                .addHeader("Authorization", "Bearer " + access_token)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
}

    public static RequestSpecification get_account_specification(){
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))

//                .setBaseUri("https://accounts.spotify.com")
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();

    }

    public static ResponseSpecification get_res_specification() {

        return new ResponseSpecBuilder().
                log(LogDetail.ALL)
//                .expectContentType(ContentType.JSON)
                .build();

    }
}
