package com.OAuth.playlIst.util;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtility.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }
    public String getUser(){
        String prop = properties.getProperty("user_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    public String getPlaylistID(){
        String prop = properties.getProperty("playlist_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property playlist_id is not specified in the config.properties file");
    }
}
