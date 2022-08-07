package com.OAuth.playlIst.api.ApplicationAPI;
import com.OAuth.playlIst.util.ConfigLoader;
import io.restassured.response.Response;

import static com.OAuth.playlIst.api.RestResource.post_account;


import java.time.Instant;
import java.util.HashMap;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String get_token(){
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("renewing token");
                Response response = renew_token();
                access_token = response.path("access_token");
                int token_expiry_duration = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(token_expiry_duration - 300);

            } else {
                System.out.println("Hello");
            }
        }catch (Exception e) {
            throw new RuntimeException("Token not renewed");
        }
        return access_token;
    }
    private static Response renew_token() {
        System.out.println("Hello from token manager");
        HashMap<String, String> form_data = new HashMap<String, String>();

        form_data.put("grant_type", ConfigLoader.getInstance().getGrantType());
        form_data.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        form_data.put("client_id", ConfigLoader.getInstance().getClientId());
        form_data.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        Response response = post_account(form_data);
        if (response.statusCode() != 200) {
            throw new RuntimeException("Token is not renewed");
        }
        else
            return response;


    }
}
