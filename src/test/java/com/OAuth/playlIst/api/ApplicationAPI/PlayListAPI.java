package com.OAuth.playlIst.api.ApplicationAPI;

import com.OAuth.playlIst.api.RestResource;
import com.OAuth.playlIst.playlistpojo.PlayListPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.OAuth.playlIst.api.Routes.PLAYLIST;
import static com.OAuth.playlIst.api.Routes.USER;


public class PlayListAPI {

    @Step
    public static Response post(PlayListPojo request_play_list, String user_id){
        String path=USER+"/"+user_id+ PLAYLIST;
        return RestResource.post(path,request_play_list);

  }
    public static Response get(String playlist_id){
        String path=PLAYLIST +"/"+ playlist_id;
        return RestResource.get(path);
    }

    public static Response update(PlayListPojo request_play_list, String playlist_id){
        String path=PLAYLIST+"/"+ playlist_id;
        return RestResource.update(path,request_play_list);

    }


}
