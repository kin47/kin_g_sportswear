package com.example.kingsportswear.di;

import com.example.kingsportswear.data.database.ProductDAO;
import com.example.kingsportswear.data.repository.AuthRepositoryImpl;
import com.example.kingsportswear.data.repository.ProductRepositoryImpl;
import com.example.kingsportswear.data.service.AuthService;
import com.example.kingsportswear.domain.repository.AuthRepository;
import com.example.kingsportswear.domain.repository.ProductRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    AuthRepository provideAuthRepository(AuthService authService) {
        return new AuthRepositoryImpl(authService);
    }

    @Provides
    ProductRepository provideProductRepository(ProductDAO service) {
        return new ProductRepositoryImpl(service);
    }
}
