package com.example.kingsportswear.di;

import android.app.Application;
import android.content.Context;

import com.example.kingsportswear.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ServiceModule.class, RepositoryModule.class})
public class AppModule {
    private MyApp application;

    public AppModule(MyApp app) {
        application = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }
}
