package com.example.kingsportswear.di;

import com.example.kingsportswear.presentation.profile.ProfileFragment;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {AppModule.class, ServiceModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(ProfileFragment profileFragment);
}