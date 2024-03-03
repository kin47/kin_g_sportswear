package com.example.kingsportswear.di;

import com.example.kingsportswear.data.repository.AuthRepositoryImpl;
import com.example.kingsportswear.data.service.AuthService;
import com.example.kingsportswear.domain.repository.AuthRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    AuthRepository provideAuthRepository(AuthService authService) {
        return new AuthRepositoryImpl(authService);
    }
}
