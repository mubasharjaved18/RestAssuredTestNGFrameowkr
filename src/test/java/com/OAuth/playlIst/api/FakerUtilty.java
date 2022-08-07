package com.OAuth.playlIst.api;

import com.github.javafaker.Faker;

public class FakerUtilty {
   static Faker faker=new Faker();
    public static String generateName(){
        return faker.regexify("[A-Za-z0-9 ,_-]{10}");
    }
    public static String generateDescription(){
        return faker.regexify("[A-Za-z0-9 ,_-]{50}");
    }
}
