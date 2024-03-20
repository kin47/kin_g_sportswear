package com.example.kingsportswear.di;

import com.example.kingsportswear.data.database.ProductDAO;
import com.example.kingsportswear.data.service.AuthService;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    @Provides
    AuthService provideAuthService() {
        return new AuthService();
    }

    @Provides
    ProductDAO provideProductDAO() {
        return new ProductDAO();
    }
}
