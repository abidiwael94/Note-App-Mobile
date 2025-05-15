package com.example.note_app_mobile;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class FirebaseInitializer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
