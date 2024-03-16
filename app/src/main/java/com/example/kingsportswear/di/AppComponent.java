package com.example.kingsportswear.di;

import com.example.kingsportswear.presentation.login.LoginFragment;
import com.example.kingsportswear.presentation.profile.ProfileFragment;
import com.example.kingsportswear.presentation.register.RegisterFragment;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {AppModule.class, ServiceModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(LoginFragment profileFragment);
    void inject(RegisterFragment registerFragment);
    void inject(ProfileFragment profileFragment);
}