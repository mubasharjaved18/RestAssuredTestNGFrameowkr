package com.OAuth.playlIst.Tests;

import com.OAuth.playlIst.api.ApplicationAPI.PlayListAPI;
import com.OAuth.playlIst.api.StatusCode;
import com.OAuth.playlIst.errorpojo.PlayListError;
import com.OAuth.playlIst.playlistpojo.PlayListPojo;
import com.OAuth.playlIst.util.DataLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.OAuth.playlIst.api.FakerUtilty.generateDescription;
import static com.OAuth.playlIst.api.FakerUtilty.generateName;
import static com.OAuth.playlIst.api.StatusCode.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreatePlayList extends Base{
    @Step
    @Description("This is the first test case")
    @Test(priority = 1, groups = "create Play List", description = "User should be able to create playlist")
    void should_Be_Able_To_Create_Playlist() {
        PlayListPojo request_play_list = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlayListAPI.post(request_play_list, DataLoader.getInstance().getUser());
        assertResponseCode(response.statusCode(), CODE_201);
        PlayListPojo response_play_list = response.as(PlayListPojo.class);
        assertPlaylistResponseData(request_play_list, response_play_list);

    }

    @Test(priority = 2, groups = "fetch play list")
    public void should_Be_Able_To_Get_Playlist() {
        PlayListPojo request_play_list = playlistBuilder("Play List Name 2", "This is description1", false);

        Response response = PlayListAPI.get(DataLoader.getInstance().getPlaylistID());
        assertResponseCode(response.statusCode(), CODE_200);
        PlayListPojo response_play_list = response.as(PlayListPojo.class);

        assertPlaylistResponseData(request_play_list, response_play_list);

    }

    @Test(priority = 3, groups = "fetch play list")
    public void should_Be_Able_To_Update_Playlist() {
        PlayListPojo request_play_list = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlayListAPI.update(request_play_list, DataLoader.getInstance().getPlaylistID());
        assertResponseCode(response.statusCode(), CODE_200);

    }

    @Test(priority = 4, groups = "error without name")
    void should_not_be_able_create_playList_wihout_name() {
        PlayListPojo request_play_list = playlistBuilder("", generateDescription(), false);

        Response response = PlayListAPI.post(request_play_list, DataLoader.getInstance().getUser());
        PlayListError playListError = response.as(PlayListError.class);

        assertError(response.statusCode(), CODE_400, playListError.getError().getMessage());
    }
    @Step
    public PlayListPojo playlistBuilder(String name, String desciption, boolean _public) {
        PlayListPojo request_play_list=new PlayListPojo();
        request_play_list.setName(name);
        request_play_list.setDescription(desciption);
        request_play_list.set_public(_public);
        return request_play_list;

    }

    public void assertPlaylistResponseData(PlayListPojo request_play_list, PlayListPojo response_play_list) {
        assertThat(request_play_list.getName(), equalTo(response_play_list.getName()));
        assertThat(request_play_list.getDescription(), equalTo(response_play_list.getDescription()));
        assertThat(request_play_list.get_public(), equalTo(response_play_list.get_public()));

    }
    @Step
    public void assertResponseCode(int expected_response_code, StatusCode statusCode) {
        assertThat(expected_response_code, equalTo(statusCode.code));
    }
    @Step
    public void assertError(int expected_response_code, StatusCode statusCode, String expected_error_message) {
        assertThat(expected_response_code, equalTo(statusCode.code));
        assertThat(expected_error_message, equalTo(statusCode.msg));


    }

}
