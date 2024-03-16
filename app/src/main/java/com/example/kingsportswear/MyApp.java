package com.example.kingsportswear;

import android.app.Application;

import com.example.kingsportswear.di.AppModule;
import com.example.kingsportswear.di.AppComponent;
import com.example.kingsportswear.di.DaggerAppComponent;
import com.google.firebase.FirebaseApp;

public class MyApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
