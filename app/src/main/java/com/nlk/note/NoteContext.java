package com.nlk.note;

import android.app.Application;

public class NoteContext extends Application {
    private static NoteContext APP;

    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
    }

    public static NoteContext getInstance() {
        return APP;
    }
}
