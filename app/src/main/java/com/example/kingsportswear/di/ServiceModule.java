package com.example.kingsportswear.di;

import com.example.kingsportswear.data.service.AuthService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {
    @Provides
    AuthService provideAuthService() {
        return new AuthService();
    }
}
