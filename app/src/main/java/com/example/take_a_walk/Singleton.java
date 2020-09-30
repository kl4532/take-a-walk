package com.example.take_a_walk;

import android.app.Application;

public class Singleton extends Application {
    private static Singleton instance;

    public void onCreate() {
        instance = this;
    }

    public static Singleton getInstance() {
        return instance;
    }

}
