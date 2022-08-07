package com.OAuth.playlIst.Tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class Base {
    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("STARTING TEST: " + m.getName());
        System.out.println("THREAD ID: " + Thread.currentThread().getId());
    }
}
