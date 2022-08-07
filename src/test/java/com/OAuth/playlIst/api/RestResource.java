package com.OAuth.playlIst.api;

import com.OAuth.playlIst.playlistpojo.PlayListPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.OAuth.playlIst.api.ApplicationAPI.TokenManager.get_token;
import static com.OAuth.playlIst.api.Routes.API;
import static com.OAuth.playlIst.api.Routes.TOKEN;
import static com.OAuth.playlIst.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {


    public static Response post(String path, Object payload){
       return given(get_req_specification())
                .body(payload)
                .when()
                .post(path)
                .then().spec(get_res_specification())
                .extract()
                .response();
  }
    public static Response get(String path){
        return given(get_req_specification())
                .when()
                .get(path)
                .then().spec(get_res_specification())
                .extract()
                .response();
    }

    public static Response update(String path ,Object payload){
        return given(get_req_specification())
                .body(payload)
                .when()
                .put(path)
                .then().spec(get_res_specification())
                .extract()
                .response();
    }
    public static Response post_account(HashMap <String,String> payload){
        return given(get_account_specification())
                .formParams(payload)
                .when()
                .post(API+TOKEN)
                .then()
                .extract()
                .response();
    }


}
